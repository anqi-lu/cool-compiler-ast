(* wrong object for method call *) 
class B {};

class Main {
    i : Int;

    init(j : Str) : SELF_TYPE {
        {
            j <- 1 + 1;
            self;
        }
    };
};

class A inherits Main {

    s : Str <- "hello";

    i : Main <- (new B).init(s);

};