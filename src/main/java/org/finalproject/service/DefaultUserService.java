package org.finalproject.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.finalproject.entities.User;
import org.finalproject.repository.RepositoryInterface;
import org.finalproject.repository.UserJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional


@RequiredArgsConstructor
public class DefaultUserService extends GeneralService <User> implements ServiceInterface <User> {

    private final UserJpaRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable){


        Page<User> departmentPage = userRepository.findAll(pageable);

        return departmentPage;

    }


    @Transactional(readOnly=true)
    @Override
    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User save(User user) {

        userRepository.save(user);
        return userRepository.getOne(user.getId());
    }


    public void update(User user) {
        user.setCreatedDate(
                userRepository .getOne(user.getId()).getCreatedDate());
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }


    @Transactional(readOnly=true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }


}
