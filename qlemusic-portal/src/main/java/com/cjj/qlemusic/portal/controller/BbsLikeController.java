package com.cjj.qlemusic.portal.controller;

import com.cjj.qlemusic.common.util.ResponseResultUtil;
import com.cjj.qlemusic.portal.entity.BbsUserLike;
import com.cjj.qlemusic.portal.service.BbsLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@Api(value = "点赞管理")
@RestController
@RequestMapping("/like")
public class BbsLikeController {
    @Autowired
    private BbsLikeService bbsLikeService;

    @ApiOperation(value = "点赞")
    @PostMapping("/bbsmusic")
    public ResponseResultUtil likeBbsMusic(@RequestBody @Validated BbsUserLike bbsUserLike, BindingResult result) {
        try {
            Map<String, Object> data = bbsLikeService.like(bbsUserLike);
            return ResponseResultUtil.success(data);
        } catch (IOException e) {
            return ResponseResultUtil.failed("点赞失败");
        }
    }
}
