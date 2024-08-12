package com.lamnhattan.example003.service;

import java.util.List;

import com.lamnhattan.example003.entity.Tag;


public interface TagService {
    Tag createTag(Tag Tag);

    Tag getTagById(Long TagId);

    List<Tag> getAllTags();

    Tag updateTag(Tag Tag);

    void deleteTag(Long TagId);
}
