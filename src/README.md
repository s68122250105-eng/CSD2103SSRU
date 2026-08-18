# stack-algorithm-group03

Group 3 — Text Editor: Snapshot vs Command/Delta (Undo/Redo with Stack), Java.

## Build & Run (requires JDK, e.g. `sudo apt install default-jdk`)

```bash
cd src
javac Main.java models/Action.java algorithms/TextEditor.java \
      algorithms/SnapshotEditor.java algorithms/CommandEditor.java \
      experiments/PerformanceExperiment.java -d ../bin
cd ..

# Run the interactive menu
java -cp bin Main

# Run the performance experiment (writes results/experiment_results.csv)
java -cp bin experiments.PerformanceExperiment
```

## Structure

```
src/
  Main.java                       - menu-driven demo, algorithm selection, validation
  models/Action.java              - Action data model (used by Algorithm B)
  algorithms/TextEditor.java      - shared interface
  algorithms/SnapshotEditor.java  - Algorithm A (Snapshot Method)
  algorithms/CommandEditor.java   - Algorithm B (Command / Delta Method)
  experiments/PerformanceExperiment.java - timed trials, exports CSV
results/
  experiment_results.csv          - measured results (see report Chapter 7 for methodology note)
  performance_chart.png           - comparison charts
```

## Note on this repository

The algorithm logic in `SnapshotEditor.java` and `CommandEditor.java` was verified for
correctness with an equivalent Python simulation (see report Appendix / Chapter 7 for
the methodology note) because the authoring sandbox only had a Java **runtime**, not the
JDK compiler. Please compile and run with a real JDK before submission and re-generate
`experiment_results.csv` with actual `javac`/`java` timings — the relative trends
(Algorithm B faster and more memory-efficient than Algorithm A on large documents)
should match, per Big-O analysis in the report.
