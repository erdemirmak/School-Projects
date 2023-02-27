using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace ConsoleApp7
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.BackgroundColor = ConsoleColor.White;
            Console.Clear();
            Console.ForegroundColor = ConsoleColor.Black;

            //Creating 9*9 array and assigning all elements as ".".
            string[,] A = new string[9, 9];
            for (int i = 0; i < 9; i++)
            {
                for (int j = 0; j < 9; j++)
                {
                    A[i, j] = ".";
                }
            }

            int final_score = 0;//Score.
            int piece_number = 1;


            int cursorx = 4, cursory = 4;   // position of cursor
            ConsoleKeyInfo cki;               // required for readkey

            // --- Static screen parts
            Console.SetCursorPosition(30, 17);
            Console.WriteLine("*Placement of the piece is done by entering ");
            Console.SetCursorPosition(30, 18);
            Console.WriteLine("the top and leftmost element of the piece.");
            Console.SetCursorPosition(30, 20);
            Console.WriteLine("[Exit: Escape]");
            Console.SetCursorPosition(4, 1);
            Console.WriteLine("The Binary Sudoku");
            Console.WriteLine("    1 2 3 4 5 6 7 8 9");

            for (int i = 2; i < 20; i += 2)
            {

                Console.SetCursorPosition(2, 1 + i + 1);
                Console.WriteLine(i / 2);

            }
            for (int i = 0; i < 18; i++)
            {
                Console.SetCursorPosition(3, 3 + i + 1);
                Console.WriteLine("|     |     |     |");
            }
            for (int i = 0; i < 24; i += 6)
            {
                Console.SetCursorPosition(3, 3 + i);
                Console.WriteLine("+-----+-----+-----+");

            }
            Console.SetCursorPosition(30, 2);
            Console.WriteLine("New Piece");
            bool flag1 = false;
            bool flag2 = true;

            do
            {
                for (int i = 4; i <= 8; i++) //Deleting previous pieces.
                {
                    Console.SetCursorPosition(30, i);
                    Console.WriteLine("           ");
                }
                Console.SetCursorPosition(50, 2);
                Console.WriteLine("Score: " + final_score);
                Console.SetCursorPosition(50, 4);
                Console.WriteLine("Piece: " + piece_number);
                Random rnd = new Random();//Generating random piece.
                string new_piece = "";
                int d1 = rnd.Next(0, 2);
                int d2 = rnd.Next(0, 2);
                int d3 = rnd.Next(0, 2);
                int d4 = rnd.Next(0, 2);

                int piece_shape = rnd.Next(1, 11);
                switch (piece_shape)
                {
                    case 1:
                        new_piece = "" + d1 + "";
                        break;
                    case 2:
                        new_piece = "" + d1 + " " + d2 + "";
                        break;
                    case 3:
                        new_piece = "" + d1 + "\r\n\n\t\t\t      " + d2 + "";
                        break;
                    case 4:
                        new_piece = "" + d1 + " " + d2 + " " + d3 + "";
                        break;
                    case 5:
                        new_piece = "" + d1 + "\r\n\n\t\t\t      " + d2 + "\r\n\n\t\t\t      " + d3 + "";
                        break;
                    case 6:
                        new_piece = "" + d1 + " " + d2 + "\r\n\n\t\t\t      " + d3 + "";
                        break;
                    case 7:
                        new_piece = "" + d1 + " " + d2 + "\r\n\n\t\t\t      " + "  " + d3 + "";
                        break;
                    case 8:
                        new_piece = "" + d1 + "\r\n\n\t\t\t      " + d2 + " " + d3 + "";
                        break;
                    case 9:
                        new_piece = "  " + d1 + "\r\n\n\t\t\t      " + d2 + " " + d3 + "";
                        break;
                    case 10:
                        new_piece = "" + d1 + " " + d2 + "\r\n\n\t\t\t      " + d3 + " " + d4 + "";
                        break;
                }
                Console.SetCursorPosition(30, 4);
                Console.WriteLine(new_piece);
                flag1 = false;
                if (piece_shape == 1)//Controlling end of the game.
                {
                    for (int i = 0; i < 9; i++)
                    {
                        for (int j = 0; j < 9; j++)
                        {
                            if (A[i, j] == ".")
                            {
                                flag1 = true;
                                break;
                            }
                        }
                        if (flag1 == true)
                        {
                            break;
                        }
                    }
                }
                else if (piece_shape == 2)
                {
                    for (int i = 0; i < 9; i++)
                    {
                        for (int j = 0; j < 8; j++)
                        {
                            if (A[i, j] == "." && A[i, j + 1] == ".")
                            {
                                flag1 = true;
                                break;
                            }
                        }
                        if (flag1 == true)
                        {
                            break;
                        }
                    }
                }
                else if (piece_shape == 3)
                {
                    for (int i = 0; i < 8; i++)
                    {
                        for (int j = 0; j < 9; j++)
                        {
                            if (A[i, j] == "." && A[i + 1, j] == ".")
                            {
                                flag1 = true;
                                break;
                            }
                        }
                        if (flag1 == true)
                        {
                            break;
                        }
                    }
                }
                else if (piece_shape == 4)
                {
                    for (int i = 0; i < 9; i++)
                    {
                        for (int j = 0; j < 7; j++)
                        {
                            if (A[i, j] == "." && A[i, j + 1] == "." && A[i, j + 2] == ".")
                            {
                                flag1 = true;
                                break;
                            }
                        }
                        if (flag1 == true)
                        {
                            break;
                        }
                    }
                }
                else if (piece_shape == 5)
                {
                    for (int i = 0; i < 7; i++)
                    {
                        for (int j = 0; j < 9; j++)
                        {
                            if (A[i, j] == "." && A[i + 1, j] == "." && A[i + 2, j] == ".")
                            {
                                flag1 = true;
                                break;
                            }
                        }
                        if (flag1 == true)
                        {
                            break;
                        }
                    }
                }
                else if (piece_shape == 6)
                {
                    for (int i = 0; i < 8; i++)
                    {
                        for (int j = 0; j < 8; j++)
                        {
                            if (A[i, j] == "." && A[i + 1, j] == "." && A[i, j + 1] == ".")
                            {
                                flag1 = true;
                                break;
                            }
                        }
                        if (flag1 == true)
                        {
                            break;
                        }
                    }
                }
                else if (piece_shape == 7)
                {
                    for (int i = 0; i < 8; i++)
                    {
                        for (int j = 0; j < 8; j++)
                        {
                            if (A[i, j] == "." && A[i, j + 1] == "." && A[i + 1, j + 1] == ".")
                            {
                                flag1 = true;
                                break;
                            }
                        }
                        if (flag1 == true)
                        {
                            break;
                        }
                    }
                }
                else if (piece_shape == 8)
                {
                    for (int i = 0; i < 8; i++)
                    {
                        for (int j = 0; j < 8; j++)
                        {
                            if (A[i, j] == "." && A[i + 1, j] == "." && A[i + 1, j + 1] == ".")
                            {
                                flag1 = true;
                                break;
                            }
                        }
                        if (flag1 == true)
                        {
                            break;
                        }
                    }
                }
                else if (piece_shape == 9)
                {
                    for (int i = 0; i < 8; i++)
                    {
                        for (int j = 0; j < 9; j++)
                        {
                            if (j != 0 && A[i, j] == "." && A[i + 1, j - 1] == "." && A[i + 1, j] == ".")
                            {
                                flag1 = true;
                                break;
                            }
                        }
                        if (flag1 == true)
                        {
                            break;
                        }
                    }
                }
                else
                {
                    for (int i = 0; i < 8; i++)
                    {
                        for (int j = 0; j < 8; j++)
                        {
                            if (A[i, j] == "." && A[i, j + 1] == "." && A[i + 1, j] == "." && A[i + 1, j + 1] == ".")
                            {
                                flag1 = true;
                                break;
                            }
                        }
                        if (flag1 == true)
                        {
                            break;
                        }
                    }
                }

                if (flag1 == false)
                {                    
                    break;
                }


                for (int i = 0; i < 17; i += 2) // Printing the array elements on the game screen.
                {
                    for (int j = 0; j < 17; j += 2)
                    {
                        Console.SetCursorPosition(4 + j, 4 + i);
                        Console.WriteLine(A[i / 2, j / 2]);

                    }
                }


                // --- Main game loop
                while (true)
                {
                    if (Console.KeyAvailable)
                    {       // true: there is a key in keyboard buffer
                        cki = Console.ReadKey(true);       // true: do not write character 

                        if (cki.Key == ConsoleKey.RightArrow && cursorx < 20)
                        {   // key and boundary control
                            Console.SetCursorPosition(cursorx, cursory);
                            cursorx += 2;
                        }
                        if (cki.Key == ConsoleKey.LeftArrow && cursorx > 4)
                        {
                            Console.SetCursorPosition(cursorx, cursory);
                            cursorx -= 2;
                        }
                        if (cki.Key == ConsoleKey.UpArrow && cursory > 4)
                        {
                            Console.SetCursorPosition(cursorx, cursory);
                            cursory -= 2;
                        }
                        if (cki.Key == ConsoleKey.DownArrow && cursory < 20)
                        {
                            Console.SetCursorPosition(cursorx, cursory);
                            cursory += 2;
                        }
                        if (cki.KeyChar == 48 + d1)// Controlling the input if it is "0" or "1".
                        {
                            try
                            {   //Placing the parts as a whole.
                                if (piece_shape == 1 && A[(cursory - 4) / 2, (cursorx - 4) / 2] == ".")
                                {
                                    A[(cursory - 4) / 2, (cursorx - 4) / 2] = Convert.ToString(d1);
                                    Console.SetCursorPosition(cursorx, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 4) / 2]);
                                    break;
                                }
                                else if (piece_shape == 2 && A[(cursory - 4) / 2, (cursorx - 4) / 2] == "." && A[(cursory - 4) / 2, (cursorx - 2) / 2] == ".")
                                {
                                    A[(cursory - 4) / 2, (cursorx - 4) / 2] = Convert.ToString(d1);
                                    A[(cursory - 4) / 2, (cursorx - 2) / 2] = Convert.ToString(d2);
                                    Console.SetCursorPosition(cursorx, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 4) / 2]);
                                    Console.SetCursorPosition(cursorx + 2, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 2) / 2]);
                                    break;

                                }
                                else if (piece_shape == 3 && A[(cursory - 4) / 2, (cursorx - 4) / 2] == "." && A[(cursory - 2) / 2, (cursorx - 4) / 2] == ".")
                                {
                                    A[(cursory - 4) / 2, (cursorx - 4) / 2] = Convert.ToString(d1);
                                    A[(cursory - 2) / 2, (cursorx - 4) / 2] = Convert.ToString(d2);
                                    Console.SetCursorPosition(cursorx, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 4) / 2]);
                                    Console.SetCursorPosition(cursorx, cursory + 2);
                                    Console.WriteLine(A[(cursory - 2) / 2, (cursorx - 4) / 2]);
                                    break;
                                }
                                else if (piece_shape == 4 && A[(cursory - 4) / 2, (cursorx - 4) / 2] == "." && A[(cursory - 4) / 2, (cursorx - 2) / 2] == "."
                                     && A[(cursory - 4) / 2, cursorx / 2] == ".")
                                {

                                    A[(cursory - 4) / 2, (cursorx - 4) / 2] = Convert.ToString(d1);
                                    A[(cursory - 4) / 2, (cursorx - 2) / 2] = Convert.ToString(d2);
                                    A[(cursory - 4) / 2, cursorx / 2] = Convert.ToString(d3);
                                    Console.SetCursorPosition(cursorx, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 4) / 2]);
                                    Console.SetCursorPosition(cursorx + 2, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 2) / 2]);
                                    Console.SetCursorPosition(cursorx + 4, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, cursorx / 2]);
                                    break;
                                }
                                else if (piece_shape == 5 && A[(cursory - 4) / 2, (cursorx - 4) / 2] == "." && A[(cursory - 2) / 2, (cursorx - 4) / 2] == "."
                                    && A[cursory / 2, (cursorx - 4) / 2] == ".")
                                {

                                    A[(cursory - 4) / 2, (cursorx - 4) / 2] = Convert.ToString(d1);
                                    A[(cursory - 2) / 2, (cursorx - 4) / 2] = Convert.ToString(d2);
                                    A[cursory / 2, (cursorx - 4) / 2] = Convert.ToString(d3);
                                    Console.SetCursorPosition(cursorx, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 4) / 2]);
                                    Console.SetCursorPosition(cursorx, cursory + 2);
                                    Console.WriteLine(A[(cursory - 2) / 2, (cursorx - 4) / 2]);
                                    Console.SetCursorPosition(cursorx, cursory + 2);
                                    Console.WriteLine(A[cursory / 2, (cursorx - 4) / 2]);
                                    break;
                                }
                                else if (piece_shape == 6 && A[(cursory - 4) / 2, (cursorx - 4) / 2] == "." && A[(cursory - 4) / 2, (cursorx - 2) / 2] == "."
                                    && A[(cursory - 2) / 2, (cursorx - 4) / 2] == ".")
                                {

                                    A[(cursory - 4) / 2, (cursorx - 4) / 2] = Convert.ToString(d1);
                                    A[(cursory - 4) / 2, (cursorx - 2) / 2] = Convert.ToString(d2);
                                    A[(cursory - 2) / 2, (cursorx - 4) / 2] = Convert.ToString(d3);
                                    Console.SetCursorPosition(cursorx, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 4) / 2]);
                                    Console.SetCursorPosition(cursorx + 2, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 2) / 2]);
                                    Console.SetCursorPosition(cursorx, cursory + 2);
                                    Console.WriteLine(A[(cursory - 2) / 2, (cursorx - 4) / 2]);
                                    break;
                                }
                                else if (piece_shape == 7 && A[(cursory - 4) / 2, (cursorx - 4) / 2] == "." && A[(cursory - 4) / 2, (cursorx - 2) / 2] == "."
                                    && A[(cursory - 2) / 2, (cursorx - 2) / 2] == ".")
                                {

                                    A[(cursory - 4) / 2, (cursorx - 4) / 2] = Convert.ToString(d1);
                                    A[(cursory - 4) / 2, (cursorx - 2) / 2] = Convert.ToString(d2);
                                    A[(cursory - 2) / 2, (cursorx - 2) / 2] = Convert.ToString(d3);
                                    Console.SetCursorPosition(cursorx, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 4) / 2]);
                                    Console.SetCursorPosition(cursorx + 2, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 2) / 2]);
                                    Console.SetCursorPosition(cursorx + 2, cursory + 2);
                                    Console.WriteLine(A[(cursory - 2) / 2, (cursorx - 2) / 2]);
                                    break;
                                }
                                else if (piece_shape == 8 && A[(cursory - 4) / 2, (cursorx - 4) / 2] == "." && A[(cursory - 2) / 2, (cursorx - 4) / 2] == "."
                                    && A[(cursory - 2) / 2, (cursorx - 2) / 2] == ".")
                                {

                                    A[(cursory - 4) / 2, (cursorx - 4) / 2] = Convert.ToString(d1);
                                    A[(cursory - 2) / 2, (cursorx - 4) / 2] = Convert.ToString(d2);
                                    A[(cursory - 2) / 2, (cursorx - 2) / 2] = Convert.ToString(d3);
                                    Console.SetCursorPosition(cursorx, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 4) / 2]);
                                    Console.SetCursorPosition(cursorx, cursory + 2);
                                    Console.WriteLine(A[(cursory - 2) / 2, (cursorx - 4) / 2]);
                                    Console.SetCursorPosition(cursorx + 2, cursory + 2);
                                    Console.WriteLine(A[(cursory - 2) / 2, (cursorx - 2) / 2]);
                                    break;
                                }
                                else if (piece_shape == 9 && A[(cursory - 4) / 2, (cursorx - 4) / 2] == "." && A[(cursory - 2) / 2, (cursorx - 6) / 2] == "."
                                    && A[(cursory - 2) / 2, (cursorx - 4) / 2] == ".")
                                {

                                    A[(cursory - 4) / 2, (cursorx - 4) / 2] = Convert.ToString(d1);
                                    A[(cursory - 2) / 2, (cursorx - 6) / 2] = Convert.ToString(d2);
                                    A[(cursory - 2) / 2, (cursorx - 4) / 2] = Convert.ToString(d3);
                                    Console.SetCursorPosition(cursorx, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 4) / 2]);
                                    Console.SetCursorPosition(cursorx - 2, cursory + 2);
                                    Console.WriteLine(A[(cursory - 2) / 2, (cursorx - 6) / 2]);
                                    Console.SetCursorPosition(cursorx, cursory + 2);
                                    Console.WriteLine(A[(cursory - 2) / 2, (cursorx - 4) / 2]);
                                    break;

                                }
                                else if (piece_shape == 10 && A[(cursory - 4) / 2, (cursorx - 4) / 2] == "." && A[(cursory - 4) / 2, (cursorx - 2) / 2] == "."
                                    && A[(cursory - 2) / 2, (cursorx - 4) / 2] == "." && A[(cursory - 2) / 2, (cursorx - 2) / 2] == ".")
                                {

                                    A[(cursory - 4) / 2, (cursorx - 4) / 2] = Convert.ToString(d1);
                                    A[(cursory - 4) / 2, (cursorx - 2) / 2] = Convert.ToString(d2);
                                    A[(cursory - 2) / 2, (cursorx - 4) / 2] = Convert.ToString(d3);
                                    A[(cursory - 2) / 2, (cursorx - 2) / 2] = Convert.ToString(d4);
                                    Console.SetCursorPosition(cursorx, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 4) / 2]);
                                    Console.SetCursorPosition(cursorx + 2, cursory);
                                    Console.WriteLine(A[(cursory - 4) / 2, (cursorx - 2) / 2]);
                                    Console.SetCursorPosition(cursorx, cursory + 2);
                                    Console.WriteLine(A[(cursory - 2) / 2, (cursorx - 4) / 2]);
                                    Console.SetCursorPosition(cursorx + 2, cursory + 2);
                                    Console.WriteLine(A[(cursory - 2) / 2, (cursorx - 2) / 2]);
                                    break;
                                }
                            }
                            catch (Exception)
                            {
                                Console.SetCursorPosition(30, 20);
                            }

                        }
                        if (cki.Key == ConsoleKey.Escape)
                        {
                            flag1 = false;
                            flag2 = false;
                            break;
                        }
                    }

                    Console.SetCursorPosition(cursorx, cursory);
                }

                for (int i = 0; i < 8; i++)// Deleting previous calculations.
                {
                    Console.SetCursorPosition(30, 9 + i);
                    Console.WriteLine("                                      ");
                }

                string[,] B = new string[9, 9] // Assigning block elements into an array.
                {
                { A[0, 0], A[0, 1], A[0, 2], A[1, 0], A[1, 1], A[1, 2], A[2, 0], A[2, 1] ,A[2, 2] },
                { A[0, 3], A[0, 4], A[0, 5], A[1, 3], A[1, 4], A[1, 5], A[2, 3], A[2, 4] ,A[2, 5] },
                { A[0, 6], A[0, 7], A[0, 8], A[1, 6], A[1, 7], A[1, 8], A[2, 6], A[2, 7] ,A[2, 8] },
                { A[3, 0], A[3, 1], A[3, 2], A[4, 0], A[4, 1], A[4, 2], A[5, 0], A[5, 1] ,A[5, 2] },
                { A[3, 3], A[3, 4], A[3, 5], A[4, 3], A[4, 4], A[4, 5], A[5, 3], A[5, 4] ,A[5, 5] },
                { A[3, 6], A[3, 7], A[3, 8], A[4, 6], A[4, 7], A[4, 8], A[5, 6], A[5, 7] ,A[5, 8] },
                { A[6, 0], A[6, 1], A[6, 2], A[7, 0], A[7, 1], A[7, 2], A[8, 0], A[8, 1] ,A[8, 2] },
                { A[6, 3], A[6, 4], A[6, 5], A[7, 3], A[7, 4], A[7, 5], A[8, 3], A[8, 4] ,A[8, 5] },
                { A[6, 6], A[6, 7], A[6, 8], A[7, 6], A[7, 7], A[7, 8], A[8, 6], A[8, 7] ,A[8, 8] }
                };

                int[] score = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; // Array that holds the calculations of filled rows, columns and blocks
                int score1 = 0;// Variable that holds the sum of "score"'s elements.
                int c_row = 0;// Number of rows completed.
                int c_column = 0;// Number of columns completed.
                int c_block = 0;// Number of blocks completed.

                bool flag = true;
                int[] condition = new int[10] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

                for (int i = 0; i < 9; i++)
                {
                    for (int j = 0; j < 9; j++)// Control of completed rows.
                    {
                        if (A[i, j] == ".")
                        {
                            flag = false;
                            break;
                        }

                    }
                    if (flag == true)
                    {
                        if (c_row == 0)
                        {
                            condition[0] = i;
                        }
                        else if (c_row == 1)
                        {
                            condition[1] = i;
                        }
                        else
                        {
                            condition[2] = i;
                        }
                        c_row += 1;
                    }

                    flag = true;
                    for (int j = 0; j < 9; j++)// Control of completed columns.
                    {
                        if (A[j, i] == ".")
                        {
                            flag = false;
                        }

                    }
                    if (flag == true)
                    {
                        if (c_column == 0)
                        {
                            condition[3] = i;
                        }
                        else if (c_column == 1)
                        {
                            condition[4] = i;
                        }
                        else
                        {
                            condition[5] = i;
                        }
                        c_column += 1;
                    }

                    flag = true;
                    for (int j = 0; j < 9; j++)// Control of completed blocks.
                    {
                        if (B[i, j] == ".")
                        {
                            flag = false;
                        }

                    }
                    if (flag == true)
                    {
                        if (c_block == 0)
                        {
                            condition[6] = i;
                        }
                        else if (c_block == 1)
                        {
                            condition[7] = i;
                        }
                        else if (c_block == 2)
                        {
                            condition[8] = i;
                        }
                        else
                        {
                            condition[9] = i;
                        }
                        c_block += 1;
                    }
                    flag = true;
                }



                flag = false;
                for (int i = 0; i < 10; i++)
                {
                    if (condition[i] != -1)
                    {
                        flag = true;
                        break;
                    }
                }
                if (flag == true)
                {
                    int c = 1;
                    for (int i = 0; i < 3; i++)// Calculations of completed rows.
                    {
                        if (condition[i] != -1)
                        {
                            for (int j = 8; -1 < j; j--)
                            {
                                if (A[condition[i], j] == "1")
                                {
                                    if (j != 8)
                                    {
                                        for (int k = j + 1; k < 9; k++)
                                        {
                                            c *= 2;
                                        }
                                        score[i] += c;
                                        c = 1;
                                    }
                                    else
                                    {
                                        score[i] += 1;
                                    }
                                }
                                else if (A[condition[i], j] == "0")
                                {
                                    score[i] += 0;
                                }

                            }
                            Console.SetCursorPosition(30, 10 + i);
                            Console.WriteLine("(" + A[condition[i], 0] + A[condition[i], 1] + A[condition[i], 2] + A[condition[i], 3] + A[condition[i], 4] + A[condition[i], 5] + A[condition[i], 6] + A[condition[i], 7] + A[condition[i], 8] + ")2 = (" + score[i] + ")10");
                            score1 += score[i];

                        }
                    }
                    for (int i = 3; i < 6; i++)// Calculations of completed columns.
                    {
                        if (condition[i] != -1)
                        {
                            for (int j = 8; -1 < j; j--)
                            {
                                if (A[j, condition[i]] == "1")
                                {
                                    if (j != 8)
                                    {
                                        for (int k = j + 1; k < 9; k++)
                                        {
                                            c *= 2;
                                        }
                                        score[i] += c;
                                        c = 1;
                                    }
                                    else
                                    {
                                        score[i] += 1;
                                    }
                                }
                                else if (A[j, condition[i]] == "0")
                                {
                                    score[i] += 0;
                                }

                            }
                            Console.SetCursorPosition(30, 10 + c_row + i - 3);
                            Console.WriteLine("(" + A[0, condition[i]] + A[1, condition[i]] + A[2, condition[i]] + A[3, condition[i]] + A[4, condition[i]] + A[5, condition[i]] + A[6, condition[i]] + A[7, condition[i]] + A[8, condition[i]] + ")2" + " = (" + score[i] + ")10");
                            score1 += score[i];

                        }
                    }
                    for (int i = 6; i < 10; i++)// Calculations of completed blocks.
                    {
                        if (condition[i] != -1)
                        {
                            for (int j = 8; -1 < j; j--)
                            {
                                if (B[condition[i], j] == "1")
                                {
                                    if (j != 8)
                                    {
                                        for (int k = j + 1; k < 9; k++)
                                        {
                                            c *= 2;
                                        }
                                        score[i] += c;
                                        c = 1;
                                    }
                                    else
                                    {
                                        score[i] += 1;
                                    }
                                }
                                else if (B[condition[i], j] == "0")
                                {
                                    score[i] += 0;
                                }

                            }
                            Console.SetCursorPosition(30, 10 + c_row + c_column + i - 6);
                            Console.WriteLine("({0}{1}{2}{3}{4}{5}{6}{7}{8})2 = ({9})10", B[condition[i], 0], B[condition[i], 1], B[condition[i], 2], B[condition[i], 3], B[condition[i], 4], B[condition[i], 5], B[condition[i], 6], B[condition[i], 7], B[condition[i], 8], score[i]);
                            Console.SetCursorPosition(30, 10 + c_row + c_column + c_block);
                            score1 += score[i];

                        }
                    }
                    int score2 = 0;
                    int counter = 0;
                    if (2 <= c_row + c_column + c_block)// Calculating the score according to the number completed and adding the result to the overall score.
                    {
                        for (int i = 0; i < 10; i++)
                        {
                            if (condition[i] != -1)
                            {
                                Console.SetCursorPosition(30 + counter * 6, 10 + c_row + c_column + c_block);
                                Console.Write(score[i]);
                                Console.SetCursorPosition(34 + counter * 6, 10 + c_row + c_column + c_block);
                                Console.WriteLine("+");
                                counter++;
                            }

                        }
                        Console.SetCursorPosition(30 + counter * 5, 10 + c_row + c_column + c_block);
                        Console.WriteLine("= " + score1);
                        score2 = score1 * (c_row + c_column + c_block);
                        Console.SetCursorPosition(30, 10 + c_row + c_column + c_block + 1);
                        Console.WriteLine(score1 + " * " + (c_row + c_column + c_block) + " = " + score2);
                        Console.SetCursorPosition(30, 9);
                        Console.WriteLine("Calculations:");
                        final_score += score2;
                    }
                    else if (c_row + c_column + c_block == 1)
                    {
                        Console.SetCursorPosition(30, 9);
                        Console.WriteLine("Calculations:");
                        final_score += score1;
                    }


                    //Deleting completed rows, columns and blocks.
                    for (int i = 0; i < 3; i++)
                    {
                        if (condition[i] != -1)
                        {
                            A[condition[i], 0] = "."; A[condition[i], 1] = "."; A[condition[i], 2] = "."; A[condition[i], 3] = "."; A[condition[i], 4] = "."; A[condition[i], 5] = ".";
                            A[condition[i], 6] = "."; A[condition[i], 7] = "."; A[condition[i], 8] = ".";
                        }
                    }

                    for (int i = 3; i < 6; i++)
                    {
                        if (condition[i] != -1)
                        {
                            A[0, condition[i]] = "."; A[1, condition[i]] = "."; A[2, condition[i]] = "."; A[3, condition[i]] = "."; A[4, condition[i]] = "."; A[5, condition[i]] = "."; 
                            A[6, condition[i]] = "."; A[7, condition[i]] = "."; A[8, condition[i]] = ".";
                        }
                    }

                    for (int i = 6; i < 10; i++)
                    {
                        if (condition[i] != -1)
                        {
                            if (condition[i] == 0)
                            {
                                A[0, 0] = "."; A[0, 1] = "."; A[0, 2] = "."; A[1, 0] = "."; A[1, 1] = "."; A[1, 2] = "."; A[2, 0] = "."; A[2, 1] = "."; A[2, 2] = ".";
                            }
                            else if (condition[i] == 1)
                            {
                                A[0, 3] = "."; A[0, 4] = "."; A[0, 5] = "."; A[1, 3] = "."; A[1, 4] = "."; A[1, 5] = "."; A[2, 3] = "."; A[2, 4] = "."; A[2, 5] = ".";
                            }
                            else if (condition[i] == 2)
                            {
                                A[0, 6] = "."; A[0, 7] = "."; A[0, 8] = "."; A[1, 6] = "."; A[1, 7] = "."; A[1, 8] = "."; A[2, 6] = "."; A[2, 7] = "."; A[2, 8] = ".";
                            }
                            else if (condition[i] == 3)
                            {
                                A[3, 0] = "."; A[3, 1] = "."; A[3, 2] = "."; A[4, 0] = "."; A[4, 1] = "."; A[4, 2] = "."; A[5, 0] = "."; A[5, 1] = "."; A[5, 2] = ".";
                            }
                            else if (condition[i] == 4)
                            {
                                A[3, 3] = "."; A[3, 4] = "."; A[3, 5] = "."; A[4, 3] = "."; A[4, 4] = "."; A[4, 5] = "."; A[5, 3] = "."; A[5, 4] = "."; A[5, 5] = ".";
                            }
                            else if (condition[i] == 5)
                            {
                                A[3, 6] = "."; A[3, 7] = "."; A[3, 8] = "."; A[4, 6] = "."; A[4, 7] = "."; A[4, 8] = "."; A[5, 6] = "."; A[5, 7] = "."; A[5, 8] = ".";
                            }
                            else if (condition[i] == 6)
                            {
                                A[6, 0] = "."; A[6, 1] = "."; A[6, 2] = "."; A[7, 0] = "."; A[7, 1] = "."; A[7, 2] = "."; A[8, 0] = "."; A[8, 1] = "."; A[8, 2] = ".";
                            }
                            else if (condition[i] == 7)
                            {
                                A[6, 3] = "."; A[6, 4] = "."; A[6, 5] = "."; A[7, 3] = "."; A[7, 4] = "."; A[7, 5] = "."; A[8, 3] = "."; A[8, 4] = "."; A[8, 5] = ".";
                            }
                            else
                            {
                                A[6, 6] = "."; A[6, 7] = "."; A[6, 8] = "."; A[7, 6] = "."; A[7, 7] = "."; A[7, 8] = "."; A[8, 6] = "."; A[8, 7] = "."; A[8, 8] = ".";
                            }

                        }
                    }
                    
                }
                piece_number += 1;

            } while (flag1==true);

            if (flag2 == false)
            {
                Console.Clear();
                Console.WriteLine("Leaving the game");
                Thread.Sleep(1000);
            }
            else
            {
                Thread.Sleep(2000);
                Console.Clear();
                Console.SetCursorPosition(50, 5);
                Console.WriteLine("Game Over");
                Console.ReadLine();
            }

            
        }
    }
}
