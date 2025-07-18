select * from TEXTS order by TEXT_WRITE_DATE desc;


SELECT
    t.*,
    CASE t.text_type
        WHEN 'COMMUNITY' THEN '게시판'
        WHEN 'NOTICE' THEN '공지사항'
        WHEN 'QUESTION' THEN '문의사항'
        ELSE '기타'
        END AS text_kr
FROM TEXTS t where text_id = 98;