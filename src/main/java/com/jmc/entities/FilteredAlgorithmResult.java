package com.jmc.entities;

import java.util.List;

public record FilteredAlgorithmResult(int station, int robotID, List<Block> exploredPath, List<Block> robotPath, List<Block> pathToUnloadingStation, List<Block> returnToShelf) {
}
