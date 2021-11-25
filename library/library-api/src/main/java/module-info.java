module home.library.api {
    requires spring.web;

    exports io.mikle.home.lib.api.exception;
    exports io.mikle.home.lib.api.model;
    exports io.mikle.home.lib.api.rest;
    exports io.mikle.home.lib.api.service;
}