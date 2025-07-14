import * as THREE from "https://unpkg.com/three@0.161.0/build/three.module.js";
import * as CANNON from "https://cdn.skypack.dev/cannon-es";

let scene, camera, renderer;
let world;
let containerBodies = [];
let diceBodies = [];
let diceMeshes = [];
let heldDice = new Set();
let gameStarted = false;
let turn = 1;
let rollCount = 0;

const canvas = document.querySelector("#canvas");
const width = 800;
const height = 800;

const raycaster = new THREE.Raycaster();
const mouse = new THREE.Vector2();

init();
animate();

function init() {

    scene = new THREE.Scene();
    camera = new THREE.PerspectiveCamera(100, width / height, 0.1, 1000);
    camera.position.set(0, 20, 3);
    camera.lookAt(0, -10, 0);

    renderer = new THREE.WebGLRenderer();
    renderer.setSize(width, height);
    renderer.domElement.style.width = width + "px";
    renderer.domElement.style.height = height + "px";
    canvas.appendChild(renderer.domElement);

    world = new CANNON.World();
    world.gravity.set(0, -9.82, 0);

    const wallThickness = 0.5;
    const containerSize = 15;

    const floorBody = new CANNON.Body({
        type: CANNON.Body.STATIC,
        shape: new CANNON.Plane(),
    });
    floorBody.quaternion.setFromEuler(-Math.PI / 2, 0, 0);
    world.addBody(floorBody);

    const floorGeo = new THREE.PlaneGeometry(
        containerSize * 2,
        containerSize * 2
    );
    const floorMat = new THREE.MeshBasicMaterial({
        color: 0x006600,
        side: THREE.DoubleSide,
    });
    const floorMesh = new THREE.Mesh(floorGeo, floorMat);
    floorMesh.rotation.x = -Math.PI / 2;
    scene.add(floorMesh);

    const wallShape = new CANNON.Box(
        new CANNON.Vec3(containerSize / 2, 5, wallThickness / 2)
    );
    const wallGeo = new THREE.BoxGeometry(containerSize, 10, wallThickness);
    const wallMat = new THREE.MeshBasicMaterial({
        color: 0x8888ff,
        wireframe: true,
    });

    [
        [0, 5, containerSize / 2],
        [0, 5, -containerSize / 2],
    ].forEach((pos) => {
        const wall = new CANNON.Body({ mass: 0, shape: wallShape });
        wall.position.set(...pos);
        world.addBody(wall);
        containerBodies.push(wall);

        const mesh = new THREE.Mesh(wallGeo, wallMat.clone());
        mesh.position.set(...pos);
        scene.add(mesh);
    });

    const sideWallShape = new CANNON.Box(
        new CANNON.Vec3(wallThickness / 2, 5, containerSize / 2)
    );
    const sideWallGeo = new THREE.BoxGeometry(wallThickness, 10, containerSize);
    [
        [containerSize / 2, 5, 0],
        [-containerSize / 2, 5, 0],
    ].forEach((pos) => {
        const wall = new CANNON.Body({ mass: 0, shape: sideWallShape });
        wall.position.set(...pos);
        world.addBody(wall);
        containerBodies.push(wall);

        const mesh = new THREE.Mesh(sideWallGeo, wallMat.clone());
        mesh.position.set(...pos);
        scene.add(mesh);
    });

    const loader = new THREE.TextureLoader();
    const materials = [
        new THREE.MeshBasicMaterial({ map: loader.load("resources/images/1_dot.png") }),
        new THREE.MeshBasicMaterial({
            map: loader.load("resources/images/2_dots.png"),
        }),
        new THREE.MeshBasicMaterial({
            map: loader.load("resources/images/3_dots.png"),
        }),
        new THREE.MeshBasicMaterial({
            map: loader.load("resources/images/4_dots.png"),
        }),
        new THREE.MeshBasicMaterial({
            map: loader.load("resources/images/5_dots.png"),
        }),
        new THREE.MeshBasicMaterial({
            map: loader.load("resources/images/6_dots.png"),
        }),
    ];

    const geometry = new THREE.BoxGeometry(2.7, 2.7, 2.7);
    const diceShape = new CANNON.Box(new CANNON.Vec3(1.5, 1.5, 1.5));

    for (let i = 0; i < 5; i++) {
        const body = new CANNON.Body({
            mass: 1,
            shape: diceShape,
            position: new CANNON.Vec3(
                Math.random() * 8 - 3,
                Math.random() * 3 + 10,
                Math.random() * 8 - 4
            ),
        });
        world.addBody(body);
        diceBodies.push(body);

        const mesh = new THREE.Mesh(geometry, materials);
        mesh.userData.index = i;
        scene.add(mesh);
        diceMeshes.push(mesh);
        console.log(diceMeshes)
    }

    const btn = document.createElement("button");
    btn.textContent = "Start Game";
    btn.style.position = "absolute";
    btn.style.top = "6%";
    btn.style.left = "5%";
    btn.style.padding = "10px 20px";
    btn.style.fontSize = "16px";
    btn.style.zIndex = "10";
    btn.style.backgroundColor = "#cfae70";
    btn.style.borderRadius = "10px";
    btn.style.border = "none";
    document.querySelector('.game-container').appendChild(btn);

    btn.addEventListener("click", () => {
        if (!gameStarted) {
            gameStarted = true;
            turn = 1;
            rollCount = 0;
            heldDice.clear();
            btn.textContent = "Roll Again";
        }
        // if (rollCount < 3) {
        rollDice();
        // }
    });

    window.addEventListener("click", (event) => {
        const rect = renderer.domElement.getBoundingClientRect();
        mouse.x = ((event.clientX - rect.left) / rect.width) * 2 - 1;
        mouse.y = -((event.clientY - rect.top) / rect.height) * 2 + 1;

        console.log(mouse.x);
        console.log(mouse.y)
        raycaster.setFromCamera(mouse, camera);
        const intersects = raycaster.intersectObjects(diceMeshes);
        console.log(intersects)

        if (intersects.length > 0) {
            const index = intersects[0].object.userData.index;
            const mesh = diceMeshes[index];
            const body = diceBodies[index];
            console.log(heldDice.has(index))
            if (!heldDice.has(index)) {
                heldDice.add(index);
                world.removeBody(body);

                const from = mesh.position.clone();
                const to = new THREE.Vector3(index * 4 - 8, 2, 15);
                smoothMove(mesh, from, to, 0.3);

                const topFace = getTopFaceIndex(mesh.quaternion);
                console.log("Held Dice Face:", topFace);
                const newQuat = getQuaternionForFace(topFace);
                mesh.quaternion.copy(newQuat);
            } else {
                heldDice.delete(index);

                const from = mesh.position.clone();
                const to = new THREE.Vector3(-6, 5, Math.random() * 4 - 2);
                smoothMove(mesh, from, to, 0.3);

                // setTimeout(() => {
                // body.position.copy(from);
                body.quaternion.copy(mesh.quaternion);
                body.velocity.set(0, 0, 0);
                body.angularVelocity.set(
                    (Math.random() - 0.5) * 5,
                    0,
                    (Math.random() - 0.5) * 5
                );
                world.addBody(body);
                // }, 350);
            }
        }
    });
}

function rollDice() {
    rollCount++;

    diceBodies.forEach((body, i) => {
        if (heldDice.has(i)) return;

        body.type = CANNON.Body.DYNAMIC;

        // 💡 살짝 튀는 듯한 느낌의 속도
        const vx = (Math.random() - 0.5) * 5; // -2.5 ~ 2.5
        const vy = Math.random() * 5 + 5; // 5 ~ 10 (위쪽으로 튐)
        const vz = (Math.random() - 0.5) * 5;

        body.velocity.set(vx, vy, vz);

        // 💡 랜덤한 회전 부여 (좀 강하게 돌리려면 범위 키움)
        body.angularVelocity.set(
            (Math.random() - 0.5) * 20,
            (Math.random() - 0.5) * 20,
            (Math.random() - 0.5) * 20
        );

        // 💡 쿼터니언 회전도 랜덤화 (살짝 바꾸기만 하면 됨)
        const q = new CANNON.Quaternion(
            Math.random() * 2 - 1,
            Math.random() * 2 - 1,
            Math.random() * 2 - 1,
            Math.random() * 2 - 1
        );
        q.normalize();
        body.quaternion.copy(q);
    });
}

function animate() {
    requestAnimationFrame(animate);
    world.step(1 / 20);

    diceMeshes.forEach((mesh, i) => {
        if (!heldDice.has(i)) {
            // hold된 주사위가 아니면 물리 위치 반영
            mesh.position.copy(diceBodies[i].position);
            mesh.quaternion.copy(diceBodies[i].quaternion);
        }
        // hold된 주사위는 smoothMove가 위치를 조절 중이므로 여기서 건드리지 않음
    });

    renderer.render(scene, camera);
}

const faceMap = {
    0: 1, // +X 방향 텍스처가 눈3일 때
    1: 2, // -X 방향이 눈4일 때
    2: 3, // +Y 방향이 눈1일 때
    3: 4, // -Y 방향이 눈6일 때
    4: 5, // +Z 방향이 눈2일 때
    5: 6, // -Z 방향이 눈5일 때
};

function getTopFaceIndex(quaternion) {
    // THREE.Quaternion을 CANNON.Quaternion으로 변환 (필요 시)
    if (quaternion instanceof THREE.Quaternion) {
        quaternion = new CANNON.Quaternion(
            quaternion.x,
            quaternion.y,
            quaternion.z,
            quaternion.w
        );
    }

    const up = new CANNON.Vec3(0, 1, 0);
    const localAxes = [
        new CANNON.Vec3(1, 0, 0),
        new CANNON.Vec3(-1, 0, 0),
        new CANNON.Vec3(0, 1, 0),
        new CANNON.Vec3(0, -1, 0),
        new CANNON.Vec3(0, 0, 1),
        new CANNON.Vec3(0, 0, -1),
    ];

    let maxDot = -Infinity;
    let topFace = -1;

    for (let i = 0; i < 6; i++) {
        const worldAxis = localAxes[i].clone();
        quaternion.vmult(worldAxis, worldAxis);
        const dot = worldAxis.dot(up);
        if (dot > maxDot) {
            maxDot = dot;
            topFace = faceMap[i];
        }
    }

    return topFace;
}

function getQuaternionForFace(face) {
    const q = new CANNON.Quaternion();
    console.log(q, face);
    switch (face) {
        case 1:
            q.setFromEuler(-0.3, 0, Math.PI / 2);
            break;
        case 2:
            q.setFromEuler(-0.3, 0, -Math.PI / 2);
            break;
        case 3:
            q.setFromEuler(-0.3, Math.PI, 0);
            break;
        case 4:
            q.setFromEuler(Math.PI - 0.3, 0, 0);
            break;
        case 5:
            q.setFromEuler(-Math.PI / 2 - 0.3, 0, 0);
            break;
        case 6:
            q.setFromEuler(Math.PI / 2 - 0.3, 0, 0);
            break;
    }
    return q;
}

function smoothMove(mesh, fromPos, toPos, duration = 0.5) {
    const start = performance.now();
    function animateMove() {
        const now = performance.now();
        const elapsed = (now - start) / 1000;
        const t = Math.min(elapsed / duration, 1);
        mesh.position.lerpVectors(fromPos, toPos, t);
        if (t < 1) {
            requestAnimationFrame(animateMove);
        }
    }
    requestAnimationFrame(animateMove);
}
