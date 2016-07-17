/*
 * Rajesh Gopidi
 */

public class QuickSort {

    int l = 0;
    int less = 0, gt = 0;

    private void swap (int[] in, int a, int b)
    {
        int t = in[a];
        in[a] = in[b];
        in[b] = t;
    }

    /* less marks start of the middle segment
     * great marks the end of the middle seg
     * all the boundaries are inclusive
     */
    public int dualPivot (int[] in, int p, int r)
    {
        int less = p + 1;
        int great = r - 1;
        int q = p + 1;
        int p1 = -1, p2 = -1;

        if (in[p] > in[r])
            swap(in , p , r); 

        p1 = in[p];
        p2 = in[r];

        while (q <= great) {
            if (in[q] < p1) {
                swap(in, q, less++);
            } else if (in[q] > p2) {
                while (q < great && in[great] > p2) 
                    great--;

                swap(in, q, great--);

                if (in[q] < p1) 
                    swap(in, q, less++);
            }
            q++;
        }
        
        swap(in, p, --less);
        swap(in, r, ++great);
        
        //grouping equal values
        if (p1 != p2) {
            int l = less + 1;
            int gt = great - 1;
            q = l + 1;
            while (q <= gt) {
                if (in[q] == p1) 
                    swap(in, q, l++);
                else if (in[q] == p2) 
                    swap(in, q, gt--);
                q++;
            }
            this.l = l;
            this.gt = gt;
        } else {
            this.l = great;
            this.gt = great;
        }
        this.less = less;
        return (great);
    }

    public int HoaresPart (int[] a, int p, int r) 
    {
        int i = p - 1;
        int j = r + 1;
        int pivot = a[p];

        while (true) {
            do {
                i++;
            } while (a[i] < pivot);
            
            do {
                j--;
            } while (a[j] > pivot);

            if (i < j) {
                int t = a[j];
                a[j] = a[i];
                a[i] = t;
            } else {
                return (j);
            }
        } 
    }

    public int threewayPart (int[] in, int p, int r) 
    {
        int q = p;
        int a = p - 1, b = p - 1;
        int pivot = in[r];

        while (q < r) {
            if (in[q] == pivot) {
                b++;
                int t = in[q];
                in[q] = in[b];
                in[b] = t;
            } else if (in[q] < pivot) {
                a++;
                b++;
                int t = in[q];
                in[q] = in[b];
                in[b] = in[a];
                in[a] = t;
            }
            q++;
        }
        
        b++;
        int t = in[b];
        in[b] = in[r];
        in[r] = t;

        l = a;
        return b;
    }

    public int LomutoPart (int[] a, int p, int r)
    {
        int pivot = a[r];
        int w = p - 1;
        int q = p;

        while (q < r) {
            if (a[q] < pivot) {
                w++;
                int t = a[w];
                a[w] = a[q];
                a[q] = t;   
            }
            q++;
        }
        w++;
        int t = a[w];
        a[w] = a[r];
        a[r] = t;

        return w;
    }

    public void quickSort (int[] in, int p, int r) 
    {
        int l = 0;

        if (p >= r) 
            return;

        if (r - p == 1) {
            if (in[p] > in[r])
                swap(in, p , r);
            return;
        }
        //int q = LomutoPart(in, p, r);
        //int q = HoaresPart(in, p, r);
        //int q = threewayPart(in, p, r);
        int q = dualPivot(in, p, r);
        less = this.less;
        quickSort(in, p, less - 1);
        quickSort(in, this.l, this.gt);
        quickSort(in, q + 1, r);
    }

    public static void main (String[] args)
    {
        //int[] in = {9,8,9,1,4,5,9,6,9};
        //int[] in = {9,8,7,6,5,4,3,2,1};
        //int[] in = {4,8,1,6,3,10,11,12,7};
        int[] in = {1, 2};
        //int[] in = {2,1,3};
        //int[] in = {9, 9, 9, 9, 9};

        QuickSort obj = new QuickSort();
        obj.quickSort(in, 0, in.length-1);

        for (int i: in) {
            System.out.print(i + ",");
        }
        System.out.println();
    }
}
