class Main {
    i : Int;

    init(j : Str) : SELF_TYPE {
        (let x : Int <- 1, y : Int <- x + 1 in {
            x + 2;
        })
    };
};