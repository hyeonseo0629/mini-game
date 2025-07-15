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
let playerTotal = 0;
let computerTotal = 0;

const canvas = document.querySelector("#canvas");
const btn = document.createElement("button");
const width = 800;
const height = 800;

const raycaster = new THREE.Raycaster();
const mouse = new THREE.Vector2();

const categories = ["ones", "twos", "threes", "fours", "fives", "sixes", "three", "four", "full", "small", "large", "yahtzee", "chance"];

const faceMap = {
    0: 1, // +X 방향 텍스처가 눈3일 때
    1: 2, // -X 방향이 눈4일 때
    2: 3, // +Y 방향이 눈1일 때
    3: 4, // -Y 방향이 눈6일 때
    4: 5, // +Z 방향이 눈2일 때
    5: 6, // -Z 방향이 눈5일 때
};

gameInit();
animate();

// 초기 설정
function gameInit() {
    btn.style.disabled = false;

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
    }

    // 버튼 설정
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

    // 마우스 클릭에 따른 이벤트
    window.addEventListener("click", (event) => {
        const rect = renderer.domElement.getBoundingClientRect();
        mouse.x = ((event.clientX - rect.left) / rect.width) * 2 - 1;
        mouse.y = -((event.clientY - rect.top) / rect.height) * 2 + 1;

        raycaster.setFromCamera(mouse, camera);
        const intersects = raycaster.intersectObjects(diceMeshes);

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

// 주사위 던지기
function rollDice() {
    if (rollCount >= 3) {
        enableCategoryClicks();
        return;
    } // 3회 초과 방지

    rollCount++;
    diceBodies.forEach((body, i) => {
        if (heldDice.has(i)) return;
        body.type = CANNON.Body.DYNAMIC;
        body.velocity.set((Math.random() - 0.5) * 5, Math.random() * 5 + 5, (Math.random() - 0.5) * 5);
        body.angularVelocity.set((Math.random() - 0.5) * 20, (Math.random() - 0.5) * 20, (Math.random() - 0.5) * 20);
    });

    if (rollCount === 3 || heldDice.size === 5) {
        setTimeout(enableCategoryClicks, 1000); // 주사위 굴림 후 약간 대기하고 점수판 활성화
    }
}

// 주사위 던질 때의 움직임 구현
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

// 주사위 윗면(선택 숫자) 얻기
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

// 주사위의 특정 눈(face)이 위로 향하도록 회전 방향(쿼터니언)을 지정함
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

// 점수판 카테고리 클릭 이벤트
function enableCategoryClicks() {
    categories.forEach(cat => {
        const cell = document.querySelector(`#board-1 #score-${cat}`);
        if (cell && cell.textContent === "-") {
            cell.classList.add("clickable");
            cell.onclick = () => {
                const values = getCurrentDiceValues();
                const scoreMap = calculateScore(values);
                cell.textContent = scoreMap[cat];
                cell.classList.remove("clickable");
                cell.onclick = null;

                rollCount = 0;
                heldDice.clear();
                updateTotalScore();
                rollDice(); // 자동으로 새 라운드 시작

                setTimeout(cpuTurn, 1000);
            };
        }
    });
}

// 굴린 주사위 값 가져오기
function getCurrentDiceValues() {
    return diceMeshes.map(m => getTopFaceIndex(m.quaternion));
}

// 주사위별 점수 계산 함수 추가
function calculateScore(values) {
    const counts = Array(7).fill(0); // 1~6
    values.forEach(v => counts[v]++);

    const total = values.reduce((a, b) => a + b, 0);
    const score = {
        ones: counts[1] * 1,
        twos: counts[2] * 2,
        threes: counts[3] * 3,
        fours: counts[4] * 4,
        fives: counts[5] * 5,
        sixes: counts[6] * 6,
        three: counts.some(c => c >= 3) ? total : 0,
        four: counts.some(c => c >= 4) ? total : 0,
        full: counts.includes(3) && counts.includes(2) ? 25 : 0,
        small: hasSmallStraight(values) ? 30 : 0,
        large: hasLargeStraight(values) ? 40 : 0,
        yahtzee: counts.includes(5) ? 50 : 0,
        chance: total,
    };
    return score;
}

function hasSmallStraight(arr) {
    const unique = [...new Set(arr)].sort();
    const str = unique.join('');
    return ['1234','2345','3456'].some(s => str.includes(s));
}

function hasLargeStraight(arr) {
    const unique = [...new Set(arr)].sort().join('');
    return unique === '12345' || unique === '23456';
}

// Total 점수 계산
function updateTotalScore() {
    playerTotal = 0;
    categories.forEach(cat => {
        const cell = document.querySelector(`#board-1 #score-${cat}`);
        if (cell && !isNaN(parseInt(cell.textContent))) {
            playerTotal += parseInt(cell.textContent);
        }
    });
    document.getElementById("score-total").textContent = total;
}

// CPU 턴 로직
function cpuTurn() {
    btn.textContent = "CPU Turn";
    btn.style.disabled = true;
    rollCount = 0;
    heldDice.clear();

    const maxRolls = 3;
    let rolls = 0;

    const intervalId = setInterval(() => {
        if (rolls < maxRolls) {
            rollDice();
            rolls++;
        } else {
            clearInterval(intervalId);
            // 3번째 굴림 끝난 뒤 1초 후 점수 선택 및 턴 전환
            setTimeout(() => {
                const values = getCurrentDiceValues();
                const scoreMap = calculateScore(values);

                let bestCat = null;
                let maxScore = -1;
                categories.forEach(cat => {
                    const cell = document.querySelector(`#board-2 #score-${cat}`);
                    if (cell && cell.textContent === "-" && scoreMap[cat] > maxScore) {
                        maxScore = scoreMap[cat];
                        bestCat = cat;
                    }
                });

                if (bestCat) {
                    const cell = document.querySelector(`#board-2 #score-${bestCat}`);
                    cell.textContent = scoreMap[bestCat];
                }

                updateCPUTotalScore();

                // 다음 턴 유저 진행 준비
                rollCount = 0;
                heldDice.clear();
                btn.textContent = "Player Turn";
                btn.style.disabled = false;
                gameStarted = false;
            }, 1000);
        }
    }, 1000);
}

function updateCPUTotalScore() {
    computerTotal = 0;
    let scoreCount = 0;
    categories.forEach(cat => {
        const cell = document.querySelector(`#board-2 #score-${cat}`);
        if (cell && !isNaN(parseInt(cell.textContent))) {
            computerTotal += parseInt(cell.textContent);
            scoreCount++;
        }

        if (scoreCount === 13) {
            btn.style.disabled = true;
            let result = "draw";
            if (playerTotal > computerTotal) {
                result = "win";
            } else if (playerTotal < computerTotal) {
                result = "lose";
            }
            location.href = "GameEndC?result=" + result;
        }
    });
    document.querySelector(`#board-2 #score-total`).textContent = total;
}