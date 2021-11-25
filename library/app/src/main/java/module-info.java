module home.library.app {
    requires spring.context;
    requires spring.boot;
    requires spring.boot.autoconfigure;

    requires spring.core;
    requires spring.beans;

    opens io.mikle.home.lib.start.app to spring.core, spring.context, spring.beans;
}