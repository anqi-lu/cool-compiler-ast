(* method not defined *) 
class A inherits Str {};
class B inherits A {};
class C {};


class Main {
    i : Int;

    init(a : Str, b : A) : SELF_TYPE {
    {
        a <- "meme";
    }
    };
};

class X inherits Main {

    a : Str <- "hello";
    b : C <- new C;

    i : Main <- (new Main).hello(a, b);
};