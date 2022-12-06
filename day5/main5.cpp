#include <fstream>
#include <iostream>
#include <stack>
#include <vector>

using namespace std;

int main() {
    ifstream in;
    in.open("day5/input");

    if (!in.is_open())
        return 1;

    string data;
    string input[8];
    for (int i = 0; i < 8; ++i) {
        getline(in, data);
        input[i] = data;
    }

    stack<char> stacks[9];
    for (int i = 7; i >= 0; --i) {
        for (int j = 0; j < 9; ++j) {
            char c = input[i][1 + 4 * j];
            if (c != ' ') {
                stacks[j].push(c);
            }
        }
    }

    getline(in, data);
    getline(in, data);

    string delimiter = " ";
    while (std::getline(in, data)) {
        size_t pos = 0;
        string token;
        vector<string> elems;
        while ((pos = data.find(delimiter)) != string::npos) {
            token = data.substr(0, pos);
            elems.push_back(token);
            data.erase(0, pos + delimiter.length());
        }
        elems.push_back(data.substr(0, data.find(delimiter)));
        int num = stoi(elems[1]);
        int start = stoi(elems[3]) - 1;
        int end = stoi(elems[5]) - 1;

        stack<char> popper;
        for (int i = 0; i < num; i++) {
            char popped = stacks[start].top();
            stacks[start].pop();
            popper.push(popped);
        }
        for (int i = 0; i < num; i++) {
            char popped = popper.top();
            popper.pop();
            stacks[end].push(popped);
        }
    }

    for (int i = 0; i < 9; i++) {
        cout << stacks[i].top();
    }
}