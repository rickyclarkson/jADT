Known Limitations
=================

* Currently you can add comments to a JADT file but there's no way to create comments that flow through to the generated Java. [track](https://github.com/JamesIry/JADT/issues/4)
* Currently there's no way to create a parameterized (generic) ADT.  So no "Option A = Some(A value) | None". Should be easy to fix, I just haven't gotten around to it. [track](https://github.com/JamesIry/JADT/issues/1)
* If an ADT has multiple constructors, none may the same name as the data type.  It's not clear how to lift that restriction and still make Java happy other than by mangling names and that wouldn't be invisible to the user.  For now, use different names.  E.g. Foo = Foo | Bar should be Foo = FooDef | Bar.