#include <stdio.h>

void interpretAsFloat(int* intPtr) {
    float* floatPtr = (float*)intPtr;  // Cast the pointer
    printf("The value interpreted as a float is: %f\n", *floatPtr);  // Dereference and use it as a float
}

int main() {
    int num = 123456;  // Example integer
    interpretAsFloat(&num);
    return 0;
}
pp
#include <iostream>
using namespace std;

void interpretAsFloat(int* intPtr) {
    float* floatPtr = reinterpret_cast<float*>(intPtr);  // Cast the pointer using reinterpret_cast
    cout << "The value interpreted as a float is: " << *floatPtr << endl;  // Dereference and use it as a float
}

int main() {
    int num = 123456;  // Example integer
    interpretAsFloat(&num);
    return 0;
}