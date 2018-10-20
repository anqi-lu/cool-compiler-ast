(* cannot use SELF_TYPE for variables *) 
class Main {
    i : SELF_TYPE;

    a() : Int{
        5 + b()
    };

    b(): Str {
        1
    };
};