package me.programmeris.myresume.api;

import lombok.RequiredArgsConstructor;
import me.programmeris.myresume.api.entity.content.Content;
import me.programmeris.myresume.api.entity.content.ContentType;
import me.programmeris.myresume.api.entity.resume.Resume;
import me.programmeris.myresume.api.entity.resume.ResumeType;
import me.programmeris.myresume.api.entity.resume.ResumeUIType;
import me.programmeris.myresume.api.entity.user.User;
import me.programmeris.myresume.api.repository.ContentRepository;
import me.programmeris.myresume.api.repository.ResumeRepository;
import me.programmeris.myresume.api.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.doInit();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final UserRepository userRepository;
        private final ResumeRepository resumeRepository;
        private final ContentRepository contentRepository;

        public void doInit() {
            User user = createUser("Donghwan Lee", "010-2041-9909", "colus4@gmail.com");
            userRepository.save(user);

            Resume resume = new Resume();
            resume.setResumeType(ResumeType.TYPE_1);
            resume.setResumeName("Default");
            resume.setUser(user);
            resume.setUseYn("Y");
            resume.setCreateUserId(user.getId());
            resume.setUpdateUserId(user.getId());
            resumeRepository.save(resume);

            Content content = new Content();
            content.setContentType(ContentType.PROFILE);
            content.setStartDate(LocalDate.of(2018, 1, 1));
            content.setEndDate(LocalDate.of(2019, 9, 10));
            content.setDisplayOrder(1);
            content.setName("Profile");
            content.setContent("내용");
            content.setDescription("설명");
            content.setCreateUserId(user.getId());
            content.setUpdateUserId(user.getId());
            contentRepository.save(content);

        }

        private User createUser(String username, String phone, String email) {
            User user = new User();
            user.setUsername(username);
            user.setPhone(phone);
            user.setEmail(email);
            user.setCreateUserId(1L);
            user.setUpdateUserId(1L);
            return user;
        }
    }
}
