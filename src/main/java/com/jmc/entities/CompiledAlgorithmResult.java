package com.jmc.entities;

import java.util.Map;

public record CompiledAlgorithmResult(Map<Integer, AlgorithmResult> results) {
}
