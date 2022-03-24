package com.wg.basics.mapper;

import com.wg.basics.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Mapper好像注入不了bean，使用@Repository，
 * spring默认在resources下找到.xml文件，打包时会忽略java包中.xml文件。导致找不到mapper
 */
@Repository
public interface BookMapper {
    /**
     * 添加书籍
     * @param book
     * @return
     */
    int addBook(Book book);

    /**
     * 删除数据
     * @param id
     * @return
     */
    int deleteBook(Integer id);

    /**
     * 修改书籍
     * @param book
     * @return
     */
    int updateBook(Book book);

    /**
     * 通过id的到单本书籍
     * @return
     */
    Book getBookById(Integer id);

    /**
     * 得到所有书籍
     * @return
     */
    List<Book> getBooksAll();
}
