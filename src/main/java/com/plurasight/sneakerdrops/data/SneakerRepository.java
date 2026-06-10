package com.plurasight.sneakerdrops.data;

import com.plurasight.sneakerdrops.models.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SneakerRepository extends JpaRepository<Sneaker, Long> {
}
