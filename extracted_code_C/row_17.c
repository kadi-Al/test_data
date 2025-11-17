#include <stdio.h>
#include <string.h>

struct A {
    int x;
};

struct B {
    float y;
};

int main() {
    struct A a;
    struct B b;
    memcpy(&a, &b, sizeof(b));
    return 0;
}
#include <stdio.h>
#include <string.h>

struct A {
    int x;
};

struct B {
    float y;
};

int main() {
    struct A a;
    struct B b;
    // Ensure the structures are compatible and copy only if they are
    if (sizeof(a) == sizeof(b)) {
        memcpy(&a, &b, sizeof(b));
    } else {
        printf("Incompatible structure sizes.\n");
    }
    return 0;
}