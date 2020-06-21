package com.wzh.springbootmusichome.repository;


import com.wzh.springbootmusichome.Entity.My;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface MyRepository extends JpaRepository<My,Integer> {
//     List<My> findByNameAndAuthorContaining(String name,String author);

}
