#include <fstream>
#include <set>
#include <map>
#include <iostream>

using namespace std;

int main() {
    ifstream in;
    in.open("day3/input");

    if (!in.is_open())
        return 1;

    string data;
    int sum = 0;
    set<char> c;
    set<char> c2;
    map<char, int> count;
    int r = 0;
    int ans = 0;
    while (std::getline(in, data)) {
        if (r % 3 == 0) {
            for (auto i = count.begin(); i != count.end(); ++i) {
                if (i->second == 3) {
                    char ca = i->first;
                    ans += ca >= 'a' ? ca - 'a' + 1 : ca - 'A' + 27;
                }
            }
            count.clear();
        }

        for (int i = 0; i < data.length() / 2; ++i) {
            c.insert(data[i]);
            c2.insert(data[i]);
        }

        for (int i = data.length() / 2; i < data.length(); ++i) {
            c2.insert(data[i]);
            if (c.find(data[i]) != c.end()) {
                char ca = data[i];
                sum += ca >= 'a' ? ca - 'a' + 1 : ca - 'A' + 27;
                c.erase(ca);
            }
        }

        for (auto i = c2.begin(); i != c2.end(); ++i) {
            count[*i] = count[*i] + 1;
        }

        c.clear();
        c2.clear();

        r++;
    }

    for (auto i = count.begin(); i != count.end(); ++i) {
        if (i->second == 3) {
            char ca = i->first;
            ans += ca >= 'a' ? ca - 'a' + 1 : ca - 'A' + 27;
        }
    }

    count.clear();
    cout << sum << endl;
    cout << ans << endl;
}