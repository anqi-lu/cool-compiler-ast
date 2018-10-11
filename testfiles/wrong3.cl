(* wrong type of parameter *) 
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

    i : Main <- (new Main).init(1);

};