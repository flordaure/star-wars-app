package com.conexaproject.star_wars_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RequestMapping(path = "/starWarsApi", produces = {APPLICATION_JSON_VALUE})
public class ApiController {
}
