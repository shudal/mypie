package moe.perci.pie.model.service;

import moe.perci.pie.model.Meta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaService extends JpaRepository<Meta, Long> {
    Meta findMetaByMetaKey(String _metaKey);
}
