package it.miketan.pb.serializer.controllers;

sealed interface IController permits MainController {

   void initialize();

}
