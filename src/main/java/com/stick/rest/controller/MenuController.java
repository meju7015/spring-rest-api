package com.stick.rest.controller;

import com.stick.rest.entity.Menu;
import com.stick.rest.model.response.ListResult;
import com.stick.rest.repository.MenuJpaRepo;
import com.stick.rest.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class MenuController {

    private final MenuJpaRepo menuJpaRepo;
    private final ResponseService responseService;

    @GetMapping(value = "/menu")
    public ListResult<Menu> getMenu() {
        return responseService.getListResult(
                menuJpaRepo.findAll()
        );
    }

}
