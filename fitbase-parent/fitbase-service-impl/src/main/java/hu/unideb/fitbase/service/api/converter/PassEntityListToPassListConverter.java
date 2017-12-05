package hu.unideb.fitbase.service.api.converter;

import hu.unideb.fitbase.persistence.entity.PassEntity;
import hu.unideb.fitbase.service.api.domain.Pass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PassEntityListToPassListConverter implements Converter<List<PassEntity>, List<Pass>> {

    @Autowired
    private PassEntityToPassConverter passEntityToPassConverter;

    @Override
    public List<Pass> convert(List<PassEntity> source) {
        return source.stream().map(pass -> passEntityToPassConverter.convert(pass)).collect(Collectors.toList());
    }

}
