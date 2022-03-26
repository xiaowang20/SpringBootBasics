package com.wg.basics.batch;

import com.wg.basics.entiry.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * 批处理配置
 * 注:
 *  1.此次批处理是将CSV文件导入数据库
 *  2.java代码流程：
 *      1.配置一个Job（里面配置step）
 *      2.配置一个step:读写的步骤
 *      3.配置读取csv文件
 *      4.配置写入数据库
 *  3.本次使用jdbc: 映射关系：cvs文件--->实体类<---sql
 */
@Configuration
public class CsvBatchConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    DataSource dataSource;

    /**
     * FlatFileItemReader
     * 注：
     *  1.本次使用的是普通的文件阅读器：读取普通文件
     *  2.扩展：常用阅读器有：JdbcPagingItemReader(读取数据库文件)、StaxEventItemReader（读取XML文件）
     * @return
     */
    @Bean
    @StepScope
    FlatFileItemReader itemReader(){
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();//需要将读取的文件与数据库文件映射，所以要引入User。
        reader.setLinesToSkip(1);//公共设置器，用于在文件开头跳过的行数。
        reader.setResource(new ClassPathResource("data.csv"));//输入资源的公共设置器。
        //映射
        reader.setLineMapper(new DefaultLineMapper<User>(){
            {
                //从文件中获得的字符串拆分为标记(分割)
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames("id","username","address","sex");//csv文件中列名
                        setDelimiter("\t");//分隔符的设置器。
                    }
                });
                //设置要映射的目标类
                setFieldSetMapper(new BeanWrapperFieldSetMapper<User>(){
                    {
                        setTargetType(User.class);
                    }
                });
            }
        });
        return reader;
    }

    /**
     * 写入逻辑
     * @return
     */
    @Bean
    JdbcBatchItemWriter itemWriter(){
        JdbcBatchItemWriter<Object> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);//用于注入目的的数据源
        //用于在写入时执行的查询字符串
        writer.setSql("insert into user(id,username,address,sex)"+"values (:id,:username,:address,:sex)");
        //将实体类与SQL中占位符一一映射
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return writer;
    }

    @Bean
    Step csvStep(){
        return stepBuilderFactory.get("csvStep")//创建一个构建器并初始化其存储库
                .<User,User>chunk(2)//构建一个步骤，以提供的大小处理块中的项目.以保持读者和作者的类型安全，
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }

    @Bean
    Job csvJob(){
        return jobBuilderFactory.get("csvJob")
                .start(csvStep())
                .build();
    }

}
