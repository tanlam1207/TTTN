package com.lamnhattan.example003.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.lamnhattan.example003.entity.Tag;
import com.lamnhattan.example003.repository.TagRepository;
import com.lamnhattan.example003.service.TagService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class TagServiceImpl implements TagService {
    private TagRepository TagRepository;

    @Override
    public Tag createTag(Tag Tag) {
        return TagRepository.save(Tag);
    }

    @Override
    public Tag getTagById(Long TagId) {
        Optional<Tag> optionalTag = TagRepository.findById(TagId);
        return optionalTag.get();
    }

    @Override
    public List<Tag> getAllTags() {
        return TagRepository.findAll();
    }

    @Override
    public Tag updateTag(Tag Tag) {
        Tag existingTag = TagRepository.findById(Tag.getId()).get();
        existingTag.setTagName(Tag.getTagName());
        existingTag.setIcon(Tag.getIcon());
        existingTag.setCreatedAt(Tag.getCreatedAt());
        existingTag.setUpdatedAt(Tag.getUpdatedAt());
        existingTag.setCreatedBy(Tag.getCreatedBy());
        existingTag.setUpdatedBy(Tag.getUpdatedBy());
        existingTag.setActive(Tag.getActive());
        Tag updatedTag = TagRepository.save(existingTag);
        return updatedTag;
    }

    @Override
    public void deleteTag(Long TagId) {
        TagRepository.deleteById(TagId);
    }

}
