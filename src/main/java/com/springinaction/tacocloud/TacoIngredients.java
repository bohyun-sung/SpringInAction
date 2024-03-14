package com.springinaction.tacocloud;

import lombok.Data;

/**
 * 테이블 간의 관계 매핑
 */
@Data
public class TacoIngredients {

    private Taco taco;

    private Ingredient ingredient;
}
