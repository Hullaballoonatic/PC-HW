#include <stdlib.h>
#include <iostream>
using namespace std;

static int curDigit, numCols, numRows;
static const char HORIZONTAL_LINE = '-', VERTICAL_LINE = '|', BLANK_SPACE = ' ';
static const int topRow = 0, leftCol = 0;
static int midRow, botRow, rightCol;

 const int topNums [8] = {    2, 3,    5, 6, 7, 8, 9, 0 };
 const int midNums [7] = {    2, 3, 4, 5, 6,    8, 9    };
 const int botNums [7] = {    2, 3,    5, 6,    8, 9, 0 };
 const int upLNums [6] = {          4, 5, 6,    8, 9, 0 };
 const int upRNums [8] = { 1, 2, 3, 4,       7, 8, 9, 0 };
 const int lowLNums [4] = {    2,          6,    8,    0 };
 const int lowRNums [9] = { 1,    3, 4, 5, 6, 7, 8, 9, 0 };


int main (void) {
  int s, n;
  while (cin >> s >> n) {

  }
  return 0;
}