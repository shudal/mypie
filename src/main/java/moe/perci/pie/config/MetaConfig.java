package moe.perci.pie.config;

import moe.perci.pie.model.Meta;
import moe.perci.pie.model.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Component
public class MetaConfig {
    public static MetaConfig metaConfig;

    @Autowired
    MetaService metaService;

    public HashMap<String, String> metas = new HashMap<>();

    @PostConstruct
    public void init() {
        metaConfig = this;
        metaConfig.metaService = this.metaService;


        List<Meta> metaList = metaConfig.metaService.findAll();
        for (int i=0; i<metaList.size(); i++) {
            metaConfig.metas.put(metaList.get(i).getMetaKey(), metaList.get(i).getMetaValue());
        }
    }

    public String getMetaByMetaKey(String _metaKey) {
        return metaConfig.metaService.findMetaByMetaKey(_metaKey).getMetaValue();
    }

    public void setMetaByMetaKey(String _metaKey, String _metaValue) {
        Meta meta =  metaConfig.metaService.findMetaByMetaKey(_metaKey);
        meta.setMetaValue(_metaValue);
        metaConfig.metaService.save(meta);
    }
}
