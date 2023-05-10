console.log("hello world!");
let planet = {};
planet.name = "Jupiter";
planet.moons = ["Io", "Europa", "Ganymede"];
planet.getDescription = function() {
    return `planet: ${this.name}, has ${this.moons.length} moons.`;
}
console.log(planet.name);
console.log(planet.moons[1]);
console.log(planet.moons.length);
console.log(planet.getDescription());
console.log(planet.diameter);


