#include <vector>
#include <list>
#include <map>
#include <set>
#include <deque>
#include <stack>
#include <bitset>
#include <algorithm>
#include <functional>
#include <numeric>
#include <queue>
#include <utility>
#include <sstream>
#include <iostream>
#include <iomanip>
#include <cstdio>
#include <cmath>
#include <cstdlib>
#include <ctime>
#include <memory>

using namespace std;

using vi = vector<int>;
using ii = pair<int,int>;
using ll = long long;
using llu = unsigned long long;
const int INF = numeric_limits<int>::max();

const int MAX = 51;

int m[MAX][MAX];

list<ii> path;

void euler(list<ii>::iterator it, int c) {
    for (int i = 1; i < MAX; ++i) {
        if (m[c][i] == 0) continue;
        m[c][i]--;
        m[i][c]--;
        euler(path.insert(it, ii(i, c)), i);
    }
}

int main() {
    int tcc;
    cin >> tcc;
    for (int tc = 1; tc <= tcc; ++tc) {
        if (tc != 1) printf("\n");
        fill(&m[0][0], &m[MAX][0], 0);
        int n;
        cin >> n;
        while (n--) {
            int a, b;
            cin >> a >> b;
            m[a][b]++; m[a][0]++;
            m[b][a]++; m[b][0]++;
        }

        bool ok = true;
        for (auto &i : m) {
            if (i[0] % 2 == 1) {
                ok = false;
                break;
            }
        }

        printf("Case #%d\n", tc);
        if (ok) {
            int j = 0; while (m[j][0] == 0) ++j;
            path.clear();
            euler(path.begin(), j);
            for (auto p : path) {
                printf("%d %d\n", p.first, p.second);
            }
        } else {
            printf("some beads may be lost\n");
        }
    }
}
/*

The MIT License (MIT)

Copyright (c) 2013 Mikhail

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/