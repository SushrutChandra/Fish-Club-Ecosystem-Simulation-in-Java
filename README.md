# Fish Club — Ecosystem Simulation in Java

## Overview
**Fish Club** is a Java-based, discrete-time simulation of a simple aquatic ecosystem.  
It models interactions between **fish**, **plants**, and **rocks** within a bounded 2D environment, showcasing emergent population dynamics, collision handling, and autonomous agent behavior.

The project emphasizes:
- **Entity-based OOP design** (`Fish`, `Plant`, `Model`, `Controller`)
- **Event-driven ecosystem updates**
- **2D spatial reasoning** with obstacle-aware movement
- **Custom deterministic randomization** using `Random131`
- **Efficient data handling** via `ArrayList` and `for-each` iteration
- **Robust exception handling** to enforce simulation constraints

---

## Simulation Rules

### Movement & Obstacles
- Each fish moves continuously in a single direction until encountering a **rock**.
- Upon collision with a rock, the fish turns randomly to a free direction (no rock in target cell).
- Rocks and plants are stationary; only fish move.

### Fish Interactions
- **Fish vs Fish**: Larger fish consumes smaller fish, absorbing its mass.
- **Fish vs Plant**: Passing over a plant reduces plant mass and increases fish mass.
- **Starvation**: Fish shrink over time if they do not eat.
- **Overgrowth**: Fish exceeding a maximum mass explode into 4–8 smaller fish with equal combined mass.

### Plant Interactions
- **Growth**: Plants increase in mass over time when not eaten.
- **Overgrowth**: Oversized plants explode into 2–9 smaller plants with equal combined mass.
- **Depletion**: Plants reaching zero mass are removed permanently.

### Extinction
- Fish or plants with mass reduced to zero are removed from the simulation.

---

## Architecture

src/fishPond/
├── Controller.java # Main entry point, handles simulation timing and UI updates
├── Model.java # Central state management of fish, plants, rocks, and water grid
├── Fish.java # Movable aquatic entity with growth, shrinking, eating, and splitting logic
├── Plant.java # Static entity with growth, shrinking, eating, and splitting logic
├── Random131.java # Custom random integer generator (replaces java.util.Random)
└── Constants.java # Global symbolic constants (ROCK, WATER, growth rates, mass thresholds, etc.)

markdown
Copy
Edit

---

## Core Components

### `Model`
- Maintains the **terrain grid** (`ROCK` / `WATER` cells).
- Tracks collections of `Fish` and `Plant` objects via `ArrayList`.
- Updates all entities per simulation tick.
- Provides collision detection and mass-transfer methods.

### `Fish`
- State: position `(row, col)`, mass, direction.
- Behavior:
  - `move()` – advances one cell in current direction.
  - `eatFish()` / `eatPlant()` – increases mass based on consumed entity.
  - `shrink()` – reduces mass over time.
  - `explode()` – splits into multiple smaller fish when exceeding size limit.
  - `redirect()` – changes direction when encountering a rock.

### `Plant`
- State: position `(row, col)`, mass.
- Behavior:
  - `grow()` – increases size over time.
  - `shrink()` – reduces mass when eaten.
  - `explode()` – splits into multiple smaller plants when exceeding size limit.

### `Controller`
- Drives the simulation loop.
- Adjusts speed via user controls (speed slider).
- Handles start/stop events.

---

## Technical Implementation Details
- **Grid Representation**:  
  - Rocks/water stored in `boolean[][] terrain` (accessed via symbolic constants).
  - Entity positions stored independently in lists.
- **Collision Handling**:  
  - Fish vs rock: immediate direction change.
  - Fish vs fish: size comparison, larger consumes smaller.
  - Fish vs plant: plant mass reduced, fish mass increased.
- **Mass Conservation**:  
  - Explosions redistribute total mass exactly across spawned entities.
- **Deterministic Randomness**:  
  - All randomness (direction changes, explosion outcomes) uses `Random131.getRandomInteger()` for reproducible simulations.

---

