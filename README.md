# Alignment-Bioinformatics
1. Global Alignment
Purpose: Aligns the entire length of two sequences, from beginning to end.
Algorithm: Typically implemented using the Needleman-Wunsch algorithm.
Suitable for: Sequences of similar length and structure.
Backtrace: Once the alignment matrix is filled, the backtrace starts from the bottom-right corner (last cell) and traces back to the top-left corner (start cell), choosing the optimal path that led to each score in the matrix.
2. Local Alignment
Purpose: Finds the most similar local subsequences between two sequences.
Algorithm: Typically implemented using the Smith-Waterman algorithm.
Suitable for: Sequences with different lengths or only partially similar regions.
Backtrace: Starts from the highest score in the matrix and traces back until a zero is encountered (indicating the end of the local alignment region). The optimal path through the matrix is followed backward.
3. Semi-Global Alignment
Purpose: Aligns the end of one sequence to the entire length of another, useful for cases where one sequence may have additional or missing parts.
Algorithm: Can be implemented as a variation of the Needleman-Wunsch or Smith-Waterman algorithms, but with modified penalties for gaps at the ends of the sequences.
Suitable for: Aligning sequences where you want to ignore the ends of one or both sequences.
Backtrace: Depends on where the best alignment ends (e.g., in one of the last cells in the matrix), then traces back to the start cell or another optimal point based on the semi-global scoring.
4. Backtrace Process
Steps:
Start from the optimal point in the alignment matrix (depends on the alignment type: bottom-right for global, highest score for local, etc.).
Move diagonally if the current cell is derived from a match/mismatch.
Move left or up if the current cell is derived from a gap.
Record each move to reconstruct the alignment.
The backtrace allows you to reconstruct the aligned sequences, including gaps, mismatches, and matches.
