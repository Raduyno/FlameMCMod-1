package me.rigelmc.rigelmcmod.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import me.rigelmc.rigelmcmod.rank.Rank;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandPermissions
{

    Rank level();

    SourceType source();

    boolean blockHostConsole() default false;
}
