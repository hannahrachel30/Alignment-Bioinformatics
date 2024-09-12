import java.lang.Math;
class Local{

    public static void main(String[] args) {
        int gap = -1, match = 1, mismatch = -1;
        String s2 = "ACCGT", s1 = "CCG";
        int l1 = s1.length();
        int l2 = s2.length();

        // Initialize array 
        int[][] a = new int[l1 + 1][l2 + 1];
        char[][] pointer = new char[l1 + 1][l2 + 1];

        
        a[0][0] =0;
        //filing first row
        for (int i = 1; i < l1 + 1; i++) {
           // current = a[i-1][0];
            a[i][0] = 0;
            pointer[i][0] = 'U';
        }
        
        //filing first column
        for (int j = 1; j < l2 + 1; j++) {
            a[0][j] = 0;
            pointer[0][j] = 'L';
        }
        
        //filling remaning cells in matrix
        for (int i = 1; i < l1 + 1; i++) {
            for (int j = 1; j < l2 + 1; j++) {
                int v;
                if (a[i-1][j] + gap>0)  v = a[i-1][j] + gap; 
                else  v =0 ;

                int h;
                if (a[i][j-1] + gap > 0)  h = a[i][j-1] + gap; 
                else  h = 0 ;

                int d = 0;
                if(s1.charAt(i-1) == s2.charAt(j-1)){ 
                    if (a[i-1][j-1] + match > 0) d = a[i-1][j-1] + match; else d = 0;}
                else {  if ( a[i-1][j-1] + mismatch > 0) d = a[i-1][j-1] + mismatch; else d = 0;}
                int k = Math.max(v,h);
                //a[i][j] =  Math.max(k,d);
                if (d >= v && d >= h) {
                    a[i][j] = d;
                    pointer[i][j] = 'D';  // Diagonal move
                } else if (v >= h) {
                    a[i][j] = v;
                    pointer[i][j] = 'U';  // Vertical move
                } else {
                    a[i][j] = h;
                    pointer[i][j] = 'L';  // Horizontal move
                }
            }
        }

        // Print the 2D array
        for (int i = 0; i < l1 + 1; i++) {
            for (int j = 0; j < l2 + 1; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();  // Print a new line after each row
        }

        //before back-tracking check highest score from entire matrix
        int max_i =0,max_j=0,max_score =0;
        for (int i = 0; i < l1 + 1; i++) {
            for (int j = 0; j < l2 + 1; j++) {
                if(a[i][j] > max_score){
                    max_score = a[i][j];
                    max_i = i; max_j = j;
                }
            }
        }
        System.out.println(max_score + " "+ max_i + " " + max_j);

        //back-tracking
        StringBuilder alignedS1 = new StringBuilder();
        StringBuilder alignedS2 = new StringBuilder();

        int i = max_i;
        int j = max_j;

       while(i > 0 && j > 0 && a[i][j] > 0) { // backtracking ends when score reaches zero.
       //while(i > 0 || j > 0 ){
            // if (i > 0) {
            //     alignedS1.insert(0, s1.charAt(i-1));
            //     alignedS2.insert(0, '-');
            //     i--;
            // } else if (j > 0) {
            //     alignedS1.insert(0, '-');
            //     alignedS2.insert(0, s2.charAt(j-1));
            //     j--;
            // } else 
            if (pointer[i][j] == 'D') {
                alignedS1.insert(0, s1.charAt(i-1));
                alignedS2.insert(0, s2.charAt(j-1));
                i--; 
                j--;
            } else if (pointer[i][j] == 'U') {
                alignedS2.insert(0, '-');
                alignedS1.insert(0, s1.charAt(i-1));
                i--;
            } else {
                alignedS2.insert(0, s2.charAt(j-1));
                alignedS1.insert(0, '-');
                j--;
            }
        }
        
         
        System.out.println("Aligned Sequence 1: " + alignedS1);
        System.out.println("Aligned Sequence 2: " + alignedS2);
    }

    
}




// import java.lang.Math;
// class SemiGlobal {

//     public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int gap = -5; 
//        // match = 4, mismatch = -2;
//        SubMatrx	A	C	G	T
// A	4	-2	1	-2
// C	-2	4	-2	1
// G	1	-2	4	-2
// T	-2	1	-2	4

//         int n = in.nextInt()
//         for(int i = 0;i<n;i++){
//             for(int j = 0;j<n;j++){

//             }
//         }
//         String s2 = "ACGT", s1 = "ACGT";
//         int l1 = s1.length();
//         int l2 = s2.length();

//         // Initialize array 
//         int[][] a = new int[l1 + 1][l2 + 1];
//         char[][] pointer = new char[l1 + 1][l2 + 1];

//         int current = 0;
//         a[0][0] =0;
//         //filing first row
//         for (int i = 1; i < l1 + 1; i++) {
//            // current = a[i-1][0];
//             a[i][0] = 0;
//             pointer[i][0] = 'U';
//         }
        
//         //filing first column
//         for (int j = 1; j < l2 + 1; j++) {
//            // current = a[0][j-1];
//             a[0][j] = 0;
//             pointer[0][j] = 'L';
//         }
        
//         //filling remaning cells in matrix
//         for (int i = 1; i < l1 + 1; i++) {
//             for (int j = 1; j < l2 + 1; j++) {
//                 int v = a[i-1][j] + gap;
//                 int h = a[i][j-1] + gap;
//                 int d =0;
//                 if(s1.charAt(i-1) == s2.charAt(j-1)){ d = a[i-1][j-1] + match;}
//                 else { d = a[i-1][j-1] + mismatch;}
//                 int k = Math.max(v,h);
//                 //a[i][j] =  Math.max(k,d);
//                 if (d >= v && d >= h) {
//                     a[i][j] = d;
//                     pointer[i][j] = 'D';  // Diagonal move
//                 } else if (v >= h) {
//                     a[i][j] = v;
//                     pointer[i][j] = 'U';  // Vertical move
//                 } else {
//                     a[i][j] = h;
//                     pointer[i][j] = 'L';  // Horizontal move
//                 }
//             }
//         }

//         // Print the 2D array
//         for (int i = 0; i < l1 + 1; i++) {
//             for (int j = 0; j < l2 + 1; j++) {
//                 System.out.print(a[i][j] + " ");
//             }
//             System.out.println();  // Print a new line after each row
//         }

//         //back-tracking
//         StringBuilder alignedS1 = new StringBuilder();
//         StringBuilder alignedS2 = new StringBuilder();

//         int i = l1;
//         int j = l2;

//         while(i>0 || j > 0){
//             if (i == 0) {  // Sequence 1 is exhausted
//                 alignedS1.insert(0, '-');
//                 alignedS2.insert(0, s2.charAt(j-1));
//                 j--;
//             } else if (j == 0) {  // Sequence 2 is exhausted
//                 alignedS1.insert(0, s1.charAt(i-1));
//                 alignedS2.insert(0, '-');
//                 i--;

//             } else if (pointer[i][j] == 'D') {
//                 alignedS1.insert(0, s1.charAt(i-1));
//                 alignedS2.insert(0, s2.charAt(j-1));
//                 i--; j--;

//             }else if (pointer[i][j] == 'U') {
//                 alignedS2.insert(0, '_');
//                 alignedS1.insert(0, s1.charAt(i-1));
//                 i--;

//             }else  {
//                 alignedS2.insert(0, s1.charAt(j-1));
//                 alignedS1.insert(0, "_");
//                 j--;
//             }
//         }
//         System.out.println("Aligned Sequence 1: " + alignedS1);
//         System.out.println("Aligned Sequence 2: " + alignedS2);
//     }

    
// }

// import java.lang.Math;

// class GlobalAlignmentExactOutput {

//     public static void main(String[] args) {
//         int gap = -5; // Penalty for gaps (based on the matrix you provided)
//         String s1 = "TTACTGC", s2 = "TGATGA";  // Sequences used for alignment
//         int l1 = s1.length();
//         int l2 = s2.length();

//         // Substitution matrix for nucleotides (A, T, C, G)
//         int[][] substitutionMatrix = {
//             // A   T   C   G
//             { 4, -1, -1, -1},  // A
//             {-1,  4, -1, -1},  // T
//             {-1, -1,  4, -1},  // C
//             {-1, -1, -1,  4}   // G
//         };

//         // Initialize matrix for scores
//         int[][] scoreMatrix = new int[l1 + 1][l2 + 1];

//         // Fill the first row and column with gap penalties
//         for (int i = 0; i <= l1; i++) {
//             scoreMatrix[i][0] = gap * i;
//         }
//         for (int j = 0; j <= l2; j++) {
//             scoreMatrix[0][j] = gap * j;
//         }

//         // Fill the rest of the matrix
//         for (int i = 1; i <= l1; i++) {
//             for (int j = 1; j <= l2; j++) {
//                 int v = scoreMatrix[i - 1][j] + gap;  // Vertical move
//                 int h = scoreMatrix[i][j - 1] + gap;  // Horizontal move

//                 // Diagonal move with substitution score
//                 int d = scoreMatrix[i - 1][j - 1] + substitutionMatrix[getIndex(s1.charAt(i - 1))][getIndex(s2.charAt(j - 1))];

//                 // Set the maximum score from vertical, horizontal, or diagonal moves
//                 scoreMatrix[i][j] = Math.max(Math.max(v, h), d);
//             }
//         }

//         // Output the final matrix
//         System.out.println("Score matrix:");
//         for (int i = 0; i <= l1; i++) {
//             for (int j = 0; j <= l2; j++) {
//                 System.out.print(scoreMatrix[i][j] + "\t");
//             }
//             System.out.println();
//         }
//     }

//     // Helper function to map nucleotide to index in substitution matrix
//     public static int getIndex(char base) {
//         switch (base) {
//             case 'A': return 0;
//             case 'T': return 1;
//             case 'C': return 2;
//             case 'G': return 3;
//             default: return -1;  // Invalid character
//         }
//     }
// }
