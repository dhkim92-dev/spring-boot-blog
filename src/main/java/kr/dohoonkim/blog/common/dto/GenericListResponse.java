package kr.dohoonkim.blog.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
@Getter
public class GenericListResponse<T> {
  private final int count;

  private final List<T> data;

  private final int page;

  private final int size;
}
