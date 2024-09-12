import java.lang.Math;
class SemiGlobal {

    public static void main(String[] args) {
        int gap = -1, match = 1, mismatch = -1;
        String s2 = "ACTG", s1 = "CT";
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
            //current = a[0][j-1];
            a[0][j] = 0;
            pointer[0][j] = 'L';
        }
        
        //filling remaning cells in matrix
        for (int i = 1; i < l1 + 1; i++) {
            for (int j = 1; j < l2 + 1; j++) {
                int v = a[i-1][j] + gap;
                int h = a[i][j-1] + gap;
                int d =0;
                if(s1.charAt(i-1) == s2.charAt(j-1)){ d = a[i-1][j-1] + match;}
                else { d = a[i-1][j-1] + mismatch;}
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
         
        //To find max ele in last row 
        int irow=0,jrow=0;
        int last_row = l1;
        int max_row = Integer.MIN_VALUE;
        for (int j = 0;j < l2 + 1; j++) {
              if (max_row < a[last_row][j]) { max_row = a[last_row][j];
                irow = last_row;
                jrow = j;
            }
        }
        System.out.println("last row max value:" + max_row + " " + irow + " " + jrow);
        
         //To find max ele in last col 
        int icol=0,jcol=0;
        int last_col = l2;
        int max_col = Integer.MIN_VALUE;
        for (int i = 0;i < l1 + 1; i++) {
              if (max_col < a[i][last_col]) { max_col = a[i][last_col];
                icol = i;
                jcol = last_col;
            }
        }
        System.out.println("last col max value:" + max_col + " " + icol + " " + jcol);

        

        //back-tracking
        StringBuilder alignedS1 = new StringBuilder();
        StringBuilder alignedS2 = new StringBuilder();

        int i = 0;
        int j = 0;
        
        // to check if high score is in lastrow or last column
        if(max_row > max_col ){
            i = irow; j = jrow;

        }
        else{
            i = icol; j = jcol;
        }

        //backtracking starts here and ends when one sequence is aligned.
        while(i > 0 || j > 0) { // backtracking continues until one sequnence is aligned
            if (i == 0) {  // Sequence 1 is exhausted
                alignedS1.insert(0, '-');
                alignedS2.insert(0, s2.charAt(j-1));
                j--;
            } else if (j == 0) {  // Sequence 2 is exhausted
                alignedS1.insert(0, s1.charAt(i-1));
                alignedS2.insert(0, '-');
                i--;
            } else if (pointer[i][j] == 'D') {
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




