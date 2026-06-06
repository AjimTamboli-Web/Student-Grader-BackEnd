package com.StudentGrader.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.StudentGrader.Entity.Question;
import com.StudentGrader.Repository.Questionrepo;

@Service
public class QuestionService {
	
	Logger log = LoggerFactory.getLogger(QuestionService.class);
	
	
	@Autowired
	private Questionrepo repo;
	
	
	@Cacheable("questions")
	public List<Question> getall()
	{
		log.info("Fetching data from Databases");
		
		return repo.findAll();
	}

}
