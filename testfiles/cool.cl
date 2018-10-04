class Main inherits IO {
    main() : SELF_TYPE {
	{
	    outStr((new Object).typeName().substr(4,1)).
	    outStr((isvoid self).typeName().substr(1,3));
	    outStr("\n");
	}
    };
};
