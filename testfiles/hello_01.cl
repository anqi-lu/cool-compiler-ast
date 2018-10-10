class B {
    hello() : B {
        1
    };
};
class Main inherits B {
    i : Int;

    init(j : Str) : SELF_TYPE {
        {
            j <- 1 + 1;
            self;
        }
    };

} ;

