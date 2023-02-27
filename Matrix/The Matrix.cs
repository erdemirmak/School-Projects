using System;

namespace TheMatrix
{
    class Program
    {
        protected void display(int x, int y, string s)
        {
            Console.SetCursorPosition(x, y);
            Console.WriteLine(s);
        }

        static void Main(string[] args)
        {
            Program p = new Program();
            Console.BackgroundColor = ConsoleColor.White;
            Console.Clear();
            Console.ForegroundColor = ConsoleColor.Black;
            p.display(20, 0, "WELCOME TO THE MATRIX");

            double giris;
            while (true)
            {


                Console.WriteLine("Choose one of the ways(For first step please press 1 then enter,for second step please press 2 then enter );");
                Console.WriteLine("1:3x3 matrix");
                Console.WriteLine("2:3x4 matrix");
                
                giris = Convert.ToDouble(Console.ReadLine());
                if (giris == 1)
                {

                    Console.WriteLine("An example of matrix");
                    Console.WriteLine("|1st    2nd   3rd|");
                    Console.WriteLine("|4th    5th   6th|");
                    Console.WriteLine("|7th    8th   9th|");
                    Console.WriteLine("----------------------------------");
                    Console.WriteLine("Please Enter The Numbers. ");
                    double a1;
                    Console.Write("Enter first number:");
                    a1 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter second number:");
                    double b1;
                    b1 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter third number:");
                    double c1;
                    c1 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter fourth number:");
                    double a2;
                    a2 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter fifth number:");
                    double b2;
                    b2 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter sixth number:");
                    double c2;
                    c2 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter seventh number:");
                    double a3;
                    a3 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter eighth number:");
                    double b3;
                    b3 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter ninth number:");
                    double c3;
                    c3 = Convert.ToDouble(Console.ReadLine());
                    Console.WriteLine(" ");
                    Console.WriteLine("Operation A:");
                    Console.WriteLine(" ");
                    Console.WriteLine("Entered Numbers:");
                    Console.WriteLine("|" + a1 + "    " + b1 + "    " + c1 + "|");
                    Console.WriteLine("|" + a2 + "    " + b2 + "    " + c2 + "|");
                    Console.WriteLine("|" + a3 + "    " + b3 + "    " + c3 + "|");
                    double sor_1 = a1 + b1 + c1;//sor_1 means sum of row 1
                    double sor_2 = a2 + b2 + c3;
                    double sor_3 = a3 + b3 + c3;
                    double sor_a = sor_1 + sor_2 + sor_3;//sor_a means sum of rows all
                    Console.WriteLine("Sum of first row is:   " + sor_1);
                    Console.WriteLine("Sum of second row is:   " + sor_2);
                    Console.WriteLine("Sum of third row is:   " + sor_3);
                    Console.WriteLine("----------------------------------");
                    double soc_1 = a1 + a2 + a3;//soc_1 means sum of column
                    double soc_2 = b1 + b2 + b3;
                    double soc_3 = c1 + c2 + c3;
                    double soc_a = soc_1 + soc_2 + soc_3;
                    Console.WriteLine("Sum of first column is:   " + soc_1);
                    Console.WriteLine("Sum of second column is:   " + soc_2);
                    Console.WriteLine("Sum of third column is:   " + soc_3);
                    Console.WriteLine("----------------------------------");
                    double sdg = a1 + b2 + c3;
                    Console.WriteLine("Sum of primary diagonal:");
                    Console.WriteLine(sdg);
                    Console.WriteLine("----------------------------------");
                    Console.WriteLine("Transpose of your matrix is:");
                    Console.WriteLine("|" + a1 + "   " + a2 + "   " + a3 + "|");
                    Console.WriteLine("|" + b1 + "   " + b2 + "   " + b3 + "|");
                    Console.WriteLine("|" + c1 + "   " + c2 + "   " + c3 + "|");
                    Console.WriteLine("----------------------------------");
                    Console.WriteLine("Determinant of your matrix is:");
                    Console.WriteLine("(" + a1 + "*" + b2 + "*" + c3 + ") + (" + b1 + "*" + c2 + "*" + a3 + ")+(" + c1 + "*" + a2 + "*" + b3 + ")-(" + c1 + "*" + b2 + "*" + a3 + ")-(" + a1 + "*" + c2 + "*" + b3 + ")-(" + b1 + "*" + a2 + "*" + c3 + ")");
                    double dtrm = (a1 * b2 * c3) + (b1 * c2 * a3) + (c1 * a2 * b3) - (c1 * b2 * a3) - (a1 * c2 * b3) - (b1 * a2 * c3);
                    Console.WriteLine("= " + dtrm);
                    Console.WriteLine("---------------------------------- ");
                    Console.WriteLine("Operation B:");
                    Console.WriteLine("---------------------------------- ");
                    string sym = "no", dia = "no", sca = "no", ide = "no", zerone = "no", prm = "no";
                    if (a1 == 1 & b2 == 1 & c3 == 1 & a2 == 0 & a3 == 0 & b1 == 0 & b3 == 0 & c1 == 0 & c2 == 0)
                    {
                        ide = "yes";
                        sca = "yes";
                        dia = "yes";
                        sym = "yes";
                    }
                    else if (a1 == b2 & a1 == c3 & b2 == c3 & a2 == 0 & a3 == 0 & b1 == 0 & b3 == 0 & c1 == 0 & c2 == 0)
                    {
                        sca = "yes";
                        dia = "yes";
                        sym = "yes";
                    }
                    else if (a2 == 0 && a3 == 0 && b1 == 0 && b3 == 0 && c1 == 0 && c2 == 0)
                    {
                        dia = "yes";
                        sym = "yes";
                    }
                    else if (a2 == b1 && a3 == c1 && b3 == c2)
                    {
                        sym = "yes";
                    }
                    if ((a1 == 1 || a1 == 0) && (a2 == 1 || a2 == 0) && (a3 == 1 || a3 == 0) && (b1 == 1 || b1 == 0) && (b2 == 1 || b2 == 0) && (b3 == 1 || b3 == 0) && (c1 == 1 || c1 == 0) && (c2 == 1 || c2 == 0) && (c3 == 1 || c3 == 0))
                    {
                        zerone = "yes";
                    }
                    if ((a1 == 1 && a2 == 0 && a3 == 0 && b1 == 0 && b2 == 1 && b3 == 0 && c1 == 0 && c2 == 0 && c3 == 1) || (a1 == 1 && a2 == 0 && a3 == 0 && b1 == 0 && b2 == 0 && b3 == 1 && c1 == 0 && c2 == 1 && c3 == 0) || (a1 == 0 && a2 == 1 && a3 == 0 && b1 == 1 && b2 == 0 && b3 == 0 && c1 == 0 && c2 == 0 && c3 == 1) || (a1 == 0 && a2 == 1 && a3 == 0 && b1 == 0 && b2 == 0 && b3 == 1 && c1 == 1 && c2 == 0 && c3 == 0) || (a1 == 0 && a2 == 0 && a3 == 1 && b1 == 0 && b2 == 1 && b3 == 0 && c1 == 1 && c2 == 0 && c3 == 0) || (a1 == 0 && a2 == 0 && a3 == 1 && b1 == 1 && b2 == 0 && b3 == 0 && c1 == 0 && c2 == 1 && c3 == 0))
                    {
                        prm = "yes";
                    }
                    Console.WriteLine("Identity Matrix:    " + ide);
                    Console.WriteLine("Scalar Matrix:      " + sca);
                    Console.WriteLine("Diagonal Matrix:    " + dia);
                    Console.WriteLine("Zero-One Matrix:    " + zerone);
                    Console.WriteLine("Symmetric Matrix:   " + sym);
                    Console.WriteLine("Permutation Matrix: " + prm);
                    Console.WriteLine("---------------------------------- ");
                    Console.WriteLine("Operation C:");
                    Console.WriteLine("---------------------------------- ");
                    double min1 = 0, min2 = 0, min3 = 0, max1 = 0, max2 = 0, max3 = 0, maxmin = 0, minmax = 0;
                    if (a1 < b1 && a1 < c1)
                        min1 = a1;
                    else if (b1 < a1 && b1 < c1)
                        min1 = b1;
                    else
                        min1 = c1;

                    if (a2 < b2 && a2 < c2)
                        min2 = a2;
                    else if (b2 < a2 && b2 < c2)
                        min2 = b2;
                    else
                        min2 = c2;

                    if (a3 < b3 && a3 < c3)
                        min3 = a3;
                    else if (b3 < a3 && b3 < c3)
                        min3 = b3;
                    else
                        min3 = c3;

                    if (a1 > a2 && a1 > a3)
                        max1 = a1;
                    else if (a2 > a1 && a2 > a3)
                        max1 = a2;
                    else
                        max1 = a3;

                    if (b1 > b2 && b1 > b3)
                        max2 = b1;
                    else if (b2 > b1 && b2 > b3)
                        max2 = b2;
                    else
                        max2 = b3;

                    if (c1 > c2 && c1 > c3)
                        max3 = c1;
                    else if (c2 > c1 && c2 > c3)
                        max3 = c2;
                    else
                        max3 = c3;

                    if (min1 > min2 && min1 > min3)
                        maxmin = min1;
                    else if (min2 > min1 && min2 > min3)
                        maxmin = min2;
                    else
                        maxmin = min3;

                    if (max1 < max2 && max1 < max3)
                        minmax = max1;
                    else if (max2 < max1 && max2 < max3)
                        minmax = max2;
                    else
                        minmax = max3;

                    Console.WriteLine("Minimum values of each row              :" + min1 + "  " + min2 + "  " + min3);
                    Console.WriteLine("Maximum values of each column           :" + max1 + "  " + max2 + "  " + max3);
                    Console.WriteLine("Maximum of minimum values of each row   :" + maxmin);
                    Console.WriteLine("Minimum of maximum values of each column:" + minmax);

                    if (maxmin == minmax)
                    {
                        Console.WriteLine("Compare maxmin to minmax: maxmin = minmax");
                    }
                    else if (maxmin < minmax)
                    {
                        Console.WriteLine("Compare maxmin to minmax: maxmin < minmax");
                    }
                    else
                    {
                        Console.WriteLine("Compare maxmin to minmax: maxmin > minmax");
                    }
                    Console.WriteLine("----------------------------------");
                    
                }

               else if (giris == 2)
                {
                    Console.WriteLine("Operation D:");
                    Console.WriteLine("----------------------------------");
                    Console.WriteLine("An example of matrix");
                    Console.WriteLine("|1th    2nd    3rd    4th|");
                    Console.WriteLine("|5th    6th    7th    8th|");
                    Console.WriteLine("|9th    10th   11th  12th|");
                    Console.WriteLine("----------------------------------");
                    double d1, d2, d3, a1, a2, a3, b1, b2, b3, c1, c2, c3;
                    Console.Write("Enter first number:");
                    a1 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter second number:");
                    b1 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter third number:");
                    c1 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter fourth number:");
                    d1 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter fifth number:");
                    a2 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter sixth number:");
                    b2 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter seventh number:");
                    c2 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter eighth number:");
                    d2 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter ninth number:");
                    a3 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter tenth number:");
                    b3 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter eleventh number:");
                    c3 = Convert.ToDouble(Console.ReadLine());
                    Console.Write("Enter twelveth number:");
                    d3 = Convert.ToDouble(Console.ReadLine());
                    Console.WriteLine("----------------------------------");
                    Console.WriteLine("Entered Numbers:");
                    Console.WriteLine("|" + a1 + "    " + b1 + "    " + c1 + "    " + d1 + "|");
                    Console.WriteLine("|" + a2 + "    " + b2 + "    " + c2 + "    " + d2 + "|");
                    Console.WriteLine("|" + a3 + "    " + b3 + "    " + c3 + "    " + d3 + "|");
                    Console.WriteLine("----------------------------------");
                    b1 = (b1 / a1);
                    c1 = (c1 / a1);
                    d1 = (d1 / a1);
                    a1 = (a1 / a1);
                    Console.WriteLine("Step One");
                    Console.WriteLine("|" + a1 + "    " + b1 + "    " + c1 + "    " + d1 + "|");
                    Console.WriteLine("|" + a2 + "    " + b2 + "    " + c2 + "    " + d2 + "|");
                    Console.WriteLine("|" + a3 + "    " + b3 + "    " + c3 + "    " + d3 + "|");
                    Console.WriteLine("----------------------------------");
                    b2 = (b2 - (b1 * a2));
                    c2 = (c2 - (c1 * a2));
                    d2 = (d2 - (d1 * a2));
                    a2 = 0;
                    Console.WriteLine("Step Two");
                    Console.WriteLine("|" + a1 + "    " + b1 + "    " + c1 + "    " + d1 + "|");
                    Console.WriteLine("|" + a2 + "    " + b2 + "    " + c2 + "    " + d2 + "|");
                    Console.WriteLine("|" + a3 + "    " + b3 + "    " + c3 + "    " + d3 + "|");
                    Console.WriteLine("----------------------------------");
                    b3 = (b3 - (b1 * a3));
                    c3 = (c3 - (c1 * a3));
                    d3 = (d3 - (d1 * a3));
                    a3 = 0;
                    Console.WriteLine("Step Three");
                    Console.WriteLine("|" + a1 + "    " + b1 + "    " + c1 + "    " + d1 + "|");
                    Console.WriteLine("|" + a2 + "    " + b2 + "    " + c2 + "    " + d2 + "|");
                    Console.WriteLine("|" + a3 + "    " + b3 + "    " + c3 + "    " + d3 + "|");
                    Console.WriteLine("----------------------------------");
                    c2 = c2 / b2;
                    d2 = d2 / b2;
                    b2 = b2 / b2;
                    Console.WriteLine("Step Four");
                    Console.WriteLine("|" + a1 + "    " + b1 + "    " + c1 + "    " + d1 + "|");
                    Console.WriteLine("|" + a2 + "    " + b2 + "    " + c2 + "    " + d2 + "|");
                    Console.WriteLine("|" + a3 + "    " + b3 + "    " + c3 + "    " + d3 + "|");
                    Console.WriteLine("----------------------------------");
                    c1 = c1 - (c2 * b1);
                    d1 = d1 - (d2 * b1);
                    b1 = 0;
                    Console.WriteLine("Step Five");
                    Console.WriteLine("|" + a1 + "    " + b1 + "    " + c1 + "    " + d1 + "|");
                    Console.WriteLine("|" + a2 + "    " + b2 + "    " + c2 + "    " + d2 + "|");
                    Console.WriteLine("|" + a3 + "    " + b3 + "    " + c3 + "    " + d3 + "|");
                    Console.WriteLine("----------------------------------");
                    a3 = a3 - (a2 * b3);
                    c3 = c3 - (c2 * b3);
                    d3 = d3 - (d2 * b3);
                    b3 = 0;
                    Console.WriteLine("Step Six");
                    Console.WriteLine("|" + a1 + "    " + b1 + "    " + c1 + "    " + d1 + "|");
                    Console.WriteLine("|" + a2 + "    " + b2 + "    " + c2 + "    " + d2 + "|");
                    Console.WriteLine("|" + a3 + "    " + b3 + "    " + c3 + "     " + d3 + "|");
                    Console.WriteLine("----------------------------------");
                    d3 = d3 / c3;
                    c3 = c3 / c3;
                    Console.WriteLine("Step Seven");
                    Console.WriteLine("|" + a1 + "    " + b1 + "    " + c1 + "    " + d1 + "|");
                    Console.WriteLine("|" + a2 + "    " + b2 + "    " + c2 + "    " + d2 + "|");
                    Console.WriteLine("|" + a3 + "    " + b3 + "    " + c3 + "    " + d3 + "|");
                    Console.WriteLine("----------------------------------");
                    d1 = d1 - (c1 * d3);
                    c1 = 0;
                    Console.WriteLine("Step Eight");
                    Console.WriteLine("|" + a1 + "    " + b1 + "    " + c1 + "    " + d1 + "|");
                    Console.WriteLine("|" + a2 + "    " + b2 + "    " + c2 + "    " + d2 + "|");
                    Console.WriteLine("|" + a3 + "    " + b3 + "    " + c3 + "    " + d3 + "|");
                    Console.WriteLine("----------------------------------");
                    d2 = d2 - (d3 * c2);
                    c2 = 0;
                    Console.WriteLine("Step Nine");
                    Console.WriteLine("|" + a1 + "    " + b1 + "    " + c1 + "    " + d1 + "|");
                    Console.WriteLine("|" + a2 + "    " + b2 + "    " + c2 + "    " + d2 + "|");
                    Console.WriteLine("|" + a3 + "    " + b3 + "    " + c3 + "    " + d3 + "|");
                    Console.WriteLine("----------------------------------");
                    Console.WriteLine("The value of unknowns (x, y, z)");
                    Console.WriteLine("x :" + d1);
                    Console.WriteLine("y :" + d2);
                    Console.WriteLine("z :" + d3);
                    
                }
                else
                {
                    Console.WriteLine("Try again.");
                    
                }
            }
        }
    }
}
