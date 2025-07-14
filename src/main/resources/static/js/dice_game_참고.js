import * as THREE from "https://unpkg.com/three@0.161.0/build/three.module.js";
import * as CANNON from "https://cdn.skypack.dev/cannon-es";

let scene, camera, renderer, world;
let diceBodies = [], diceMeshes = [], held = new Set();
let raycaster = new THREE.Raycaster(), mouse = new THREE.Vector2();
let turn = 1, rollCount = 0;
const maxRoll = 3;
const categories = ["ones","twos","threes","fours","fives","sixes","three","four","full","small","large","yahtzee","chance"];

const canvas = document.querySelector("#canvas");
const width = 1000, height = 700;

init3D();
animate();
initUi();

function init3D() {
    scene = new THREE.Scene();
    camera = new THREE.PerspectiveCamera(60, width / height, 0.1, 1000);
    camera.position.set(0, 25, 25);
    camera.lookAt(0, 0, 0);

    renderer = new THREE.WebGLRenderer();
    renderer.setSize(width, height);
    canvas.appendChild(renderer.domElement);

    world = new CANNON.World();
    world.gravity.set(0, -9.82, 0);

    const groundMat = new CANNON.Material();
    const floorBody = new CANNON.Body({ mass: 0, material: groundMat });
    floorBody.addShape(new CANNON.Plane());
    floorBody.quaternion.setFromEuler(-Math.PI / 2, 0, 0);
    world.addBody(floorBody);

    const floorGeo = new THREE.PlaneGeometry(36, 36);
    const floorMat = new THREE.MeshBasicMaterial({ color: 0x006600, side: THREE.DoubleSide });
    const floorMesh = new THREE.Mesh(floorGeo, floorMat);
    floorMesh.rotation.x = -Math.PI / 2;
    scene.add(floorMesh);

    const wallThickness = 1, wallHeight = 10, wallLength = 36;
    const wallGeo1 = new CANNON.Box(new CANNON.Vec3(wallLength / 2, wallHeight / 2, wallThickness / 2));
    const wallGeo2 = new CANNON.Box(new CANNON.Vec3(wallThickness / 2, wallHeight / 2, wallLength / 2));
    const positions = [
        [0, wallHeight / 2, -wallLength / 2],
        [0, wallHeight / 2, wallLength / 2],
        [-wallLength / 2, wallHeight / 2, 0],
        [wallLength / 2, wallHeight / 2, 0],
    ];
    positions.forEach(([x, y, z], idx) => {
        const shape = idx < 2 ? wallGeo1 : wallGeo2;
        const body = new CANNON.Body({ mass: 0 });
        body.addShape(shape);
        body.position.set(x, y, z);
        world.addBody(body);
    });

    const loader = new THREE.TextureLoader();
    const materials = [1,2,3,4,5,6].map(i => new THREE.MeshBasicMaterial({ map: loader.load(`resources/images/${i}_dots.png`) }));
    const geometry = new THREE.BoxGeometry(2.7, 2.7, 2.7);
    const diceShape = new CANNON.Box(new CANNON.Vec3(1.35, 1.35, 1.35));

    for (let i = 0; i < 5; i++) {
        const body = new CANNON.Body({ mass: 1, shape: diceShape, position: new CANNON.Vec3(Math.random()*8-4, 10, Math.random()*8-4) });
        world.addBody(body);
        diceBodies.push(body);

        const mesh = new THREE.Mesh(geometry, materials);
        mesh.userData.index = i;
        scene.add(mesh);
        diceMeshes.push(mesh);
    }

    window.addEventListener("click", onMouseClick);
}

function animate() {
    requestAnimationFrame(animate);
    world.step(1 / 20);
    diceMeshes.forEach((mesh, i) => {
        if (!held.has(i)) {
            mesh.position.copy(diceBodies[i].position);
            mesh.quaternion.copy(diceBodies[i].quaternion);
        }
    });
    renderer.render(scene, camera);
}

function initUi() {
    const btn = document.createElement("button");
    btn.textContent = "Roll Dice";
    btn.style.position = "absolute";
    btn.style.top = "20px";
    btn.style.left = "20px";
    document.querySelector('.game-container').appendChild(btn);
    btn.onclick = rollHandler;
}

function rollHandler() {
    if (rollCount >= maxRoll) return;
    rollCount++;
    diceBodies.forEach((body, i) => {
        if (held.has(i)) return;
        body.type = CANNON.Body.DYNAMIC;
        body.velocity.set((Math.random()-0.5)*5, Math.random()*5+5, (Math.random()-0.5)*5);
        body.angularVelocity.set((Math.random()-0.5)*20, (Math.random()-0.5)*20, (Math.random()-0.5)*20);
    });
    if (rollCount === maxRoll) attachCategoryClicks();
}

function onMouseClick(event) {
    const rect = renderer.domElement.getBoundingClientRect();
    mouse.x = ((event.clientX - rect.left) / rect.width) * 2 - 1;
    mouse.y = -((event.clientY - rect.top) / rect.height) * 2 + 1;
    raycaster.setFromCamera(mouse, camera);
    const hits = raycaster.intersectObjects(diceMeshes);
    if (hits.length) toggleHold(hits[0].object.userData.index);
}

function toggleHold(i) {
    if (rollCount === 0 || rollCount > maxRoll) return;
    if (held.has(i)) held.delete(i);
    else held.add(i);
    diceMeshes[i].material.emissive = new THREE.Color(held.has(i) ? 0x444444 : 0);
}

function attachCategoryClicks() {
    categories.forEach(cat => {
        const cell = document.getElementById(`score-${turn}-${cat}`);
        if (cell && cell.textContent === "-") {
            cell.classList.add("editable");
            cell.onclick = () => selectCategory(cat, cell);
        }
    });
}

function selectCategory(cat, cell) {
    const values = getCurrentDiceValues();
    const score = calculateScore(values)[cat];
    cell.textContent = score;
    cell.classList.remove("editable");
    categories.forEach(c => {
        const other = document.getElementById(`score-${turn}-${c}`);
        if (other) other.classList.remove("editable"), other.onclick = null;
    });
    nextTurn();
}

function nextTurn() {
    turn = turn === 1 ? 2 : 1;
    rollCount = 0;
    held.clear();
    reset3D();
    document.getElementById("board-1").classList.toggle("active", turn === 1);
    document.getElementById("board-2").classList.toggle("active", turn === 2);
}

function reset3D() {
    diceBodies.forEach((body, i) => {
        world.removeBody(body);
        const newBody = new CANNON.Body({ mass: 1, shape: body.shapes[0], position: new CANNON.Vec3(Math.random()*8-4, 10, Math.random()*8-4) });
        diceBodies[i] = newBody;
        world.addBody(newBody);
        diceMeshes[i].position.set(0, 10, 0);
        diceMeshes[i].quaternion.set(0, 0, 0, 1);
    });
}

function getCurrentDiceValues() {
    return diceMeshes.map(m => getTopFaceIndex(m.quaternion));
}

function calculateScore(values) {
    const counts = Array(7).fill(0);
    values.forEach(v => counts[v]++);
    const total = values.reduce((a,b) => a+b, 0);
    return {
        ones: counts[1]*1,
        twos: counts[2]*2,
        threes: counts[3]*3,
        fours: counts[4]*4,
        fives: counts[5]*5,
        sixes: counts[6]*6,
        three: counts.some(c=>c>=3) ? total : 0,
        four: counts.some(c=>c>=4) ? total : 0,
        full: counts.includes(3)&&counts.includes(2) ? 25 : 0,
        small: hasSmallStraight(values) ? 30 : 0,
        large: hasLargeStraight(values) ? 40 : 0,
        yahtzee: counts.includes(5) ? 50 : 0,
        chance: total
    };
}

function hasSmallStraight(arr) {
    const unique = [...new Set(arr)].sort();
    const str = unique.join('');
    return ['1234','2345','3456'].some(s=>str.includes(s));
}

function hasLargeStraight(arr) {
    const unique = [...new Set(arr)].sort().join('');
    return unique === '12345' || unique === '23456';
}

const faceMap = { 0: 1, 1: 2, 2: 3, 3: 4, 4: 5, 5: 6 };
function getTopFaceIndex(quat) {
    if (quat instanceof THREE.Quaternion) {
        quat = new CANNON.Quaternion(quat.x, quat.y, quat.z, quat.w);
    }
    const up = new CANNON.Vec3(0,1,0);
    const localAxes = [
        new CANNON.Vec3(1,0,0), new CANNON.Vec3(-1,0,0),
        new CANNON.Vec3(0,1,0), new CANNON.Vec3(0,-1,0),
        new CANNON.Vec3(0,0,1), new CANNON.Vec3(0,0,-1),
    ];
    let maxDot = -Infinity, topFace = -1;
    for (let i=0;i<6;i++) {
        const worldAxis = localAxes[i].clone();
        quat.vmult(worldAxis, worldAxis);
        const dot = worldAxis.dot(up);
        if (dot > maxDot) { maxDot = dot; topFace = faceMap[i]; }
    }
    return topFace;
}
