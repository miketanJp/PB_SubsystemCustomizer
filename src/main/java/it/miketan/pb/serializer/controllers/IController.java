package it.miketan.pb.serializer.controllers;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

sealed interface IController permits MainController {

    void initialize() throws FileNotFoundException, MalformedURLException;

}
