package com.joon.blog.test;

import com.joon.blog.model.RoleType;
import com.joon.blog.model.User;
import com.joon.blog.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입 Dependency Injection - DI
    private UserRepository userRepository;

    /**
     * DELETE 테스트
     *
     * @param id: 삭제할 대상 아이디
     * @return
     */
    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);

        } catch (Exception e) {
            return "삭제에 실패하였습니다. 해당 id는 DB에 존재 X";
        }
        return "sevvces";
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
        System.out.println("id: " + id);
        System.out.println("password: " + requestUser.getPassword());
        System.out.println("email: " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다. 존재하지 않는 유저");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        /*

        &#xAE30;&#xC874;&#xC758; User&#xB97C; id&#xB85C; &#xAC00;&#xC838;&#xC624;&#xACE0; &#xADF8; &#xC704;&#xC5D0; &#xB36E;&#xC5B4; &#xC50C;&#xC6B0;&#xAE30; Put mapping &#xC218;&#xC815;
        &#xC218;&#xC815; &#xC5F0;&#xC0B0;&#xC5D0;&#xB294; save &#xB294; &#xC0AC;&#xC6A9;&#xD558;&#xC9C0;&#xC54A;&#xC74C;
         */

//        userRepository.save(user);
        return user;
    }

    @GetMapping("/dummy/user")
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user/page")
    public List<User> pageList(@PageableDefault(size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // findById의 return 객체는 Optional이다.
//        User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//            @Override
//            public User get() {
//                return new User();
//            }
//        });
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id: " + id);
            }
        });
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user) {
        System.out.println("id : " + user.getId());
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());
        System.out.println("role : " + user.getRole());
        System.out.println("createDate : " + user.getCreateDate());
        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입 성공";
    }
}