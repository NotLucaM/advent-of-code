#include "string"
#include "iostream"
#include "fstream"
#include "queue"

int main() {
    std::ifstream in;
    in.open("day1/input");

    if (!in.is_open())
        return 1;

    std::priority_queue<int> ans;
    int sum = 0;
    std::string data;
    while (std::getline(in, data)) {
        if (data == "\n" || data == "") {
            ans.push(sum);
            sum = 0;
        } else {
            sum += std::stoi(data);
        }
    }

    std::cout << ans.top() << std::endl;

    int p2 = 0;
    for (int i; i < 3; i++) {
        p2 += ans.top();
        ans.pop();
    }

    std::cout << p2 << std::endl;
}