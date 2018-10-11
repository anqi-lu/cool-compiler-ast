(* wrong var type for given expression *) 
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

    i : Int <- (new Main).init(s);

};