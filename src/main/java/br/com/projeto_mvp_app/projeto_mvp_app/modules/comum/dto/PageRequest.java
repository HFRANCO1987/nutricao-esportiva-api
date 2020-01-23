package br.com.projeto_mvp_app.projeto_mvp_app.modules.comum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
public class PageRequest implements Pageable {

    private static final Integer LIMITE = 10;
    private static final String ORDER_DIRECTION = "ASC";

    private int page;
    private int size;
    private int limit;
    private String orderBy;
    private String orderDirection;

    public PageRequest() {
        this.page = 0;
        this.size = LIMITE;
        this.limit = LIMITE;
        this.orderBy = "id";
        this.orderDirection = ORDER_DIRECTION;
    }

    @Override
    public long getOffset() {
        return page * size;
    }

    @Override
    public int getPageNumber() {
        return page;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public Sort getSort() {
        return Sort.by(Sort.Direction.fromString(this.orderDirection), this.orderBy);
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
