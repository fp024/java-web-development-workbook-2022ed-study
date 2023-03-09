package org.fp024.w2.service;

import org.fp024.w2.dao.MemberDAO;
import org.fp024.w2.domain.MemberVO;
import org.fp024.w2.dto.MemberDTO;
import org.fp024.w2.util.MapperUtil;
import org.modelmapper.ModelMapper;

public enum MemberService {
  INSTANCE;

  private final MemberDAO dao;
  private final ModelMapper modelMapper;

  MemberService() {
    dao = new MemberDAO();
    modelMapper = MapperUtil.INSTANCE.get();
  }

  public MemberDTO login(String mid, String mpw) throws Exception {
    MemberVO vo = dao.getWithPassword(mid, mpw);

    MemberDTO memberDTO = modelMapper.map(vo, MemberDTO.class);

    return memberDTO;
  }

  public void updateUuid(String mid, String uuid) throws Exception {
    dao.updateUuid(mid, uuid);
  }

  public MemberDTO getByUUID(String uuid) throws Exception {
    MemberVO memberVO = dao.selectUUID(uuid);
    MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);

    return memberDTO;
  }
}
