class A inherits Str {};
class B inherits Str {};
class C inherits Str {};


class Main {
    i : Int;

    init(a : Str, b : Str) : SELF_TYPE {
    {
        a <- "meme";
    }
    };
};

class X inherits Main {

    a : Str <- "hello";
    b : A <- new A;

    i : Main <- (new Main).init(a, b);
};